package de.amirrocker.hadesGatekeeper.cqs

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.offer.CoverPrice
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyVersionStatus
import org.assertj.core.api.Assertions
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period
import java.util.*

class TestPolicyCreation {


    @Test
    internal fun `test we can convert an offer to a policy before it expires`() {
//        given:
        val offer = OffersTestDataBuilder
                .standardOneYearContractOfferValidUntil(
                        LocalDate.of(2021, 12, 31))

        val purchaseDate = LocalDate.of(2019, 12, 31)
        val policyStartDate = LocalDate.of(2019, 12, 31)

//        when:
        val policy = offer?.let { Policy.convertOffer(it, purchaseDate, policyStartDate) }

//        then:
//        assertNotNull(policy?.getPolicyVersions()?.let { it.versions.isEmpty() }!!)
        assertNotNull(policy)
        assertNotNull(policy?.getPolicyVersions()!!)
        assertThat("Expect versions to be Not EMPTY but was ${policy.getPolicyVersions()}", policy?.getPolicyVersions()?.let { it.versions.isNotEmpty() }!!)
    }

    private val ASSISTANCE = "ASSISTANCE"
    private val OC = "OC"


    @Test
    internal fun `test that we can extend coverage with first day of policy`() {

//         given:

        val product = ProductsTestDataBuilder.standardCarInsurance()
        val policy = PoliciesTestBuilder.standardOneYearPolicy(LocalDate.of(2020, 1, 1 ))
        val newCover = product.getCovers().withCode(ASSISTANCE).get()
        val coverPrice = CoverPrice(newCover.code, MonetaryAmount.Companion.from("100")!!, Period.ofDays(356))

//        when:
        policy.extendCoverage(
                LocalDate.of(2020, 1, 1),
                newCover.code,
                coverPrice.price.toBigDecimal(),
                Period.ofDays(365)
        )

//        then:
        policy.policyVersions.size() == 2

        val firstVersion = policy.policyVersions.withNumber(1).get()
        assertTrue(firstVersion.versionStatus == PolicyVersionStatus.Active, "Expect a true value but was ${firstVersion.versionStatus}")
        assertTrue(firstVersion.covers[0].coverCode == OC, "Expect a true value but was ${newCover.code}")

        val coverFirstVersionSize = firstVersion.covers.size
        val secondVersion = policy.policyVersions.withNumber(1).get()

        assertTrue(secondVersion.versionStatus == PolicyVersionStatus.Active, "Expect a versionStatus value of Draft but was ${secondVersion.versionStatus}")
        assertTrue(secondVersion.covers.size == coverFirstVersionSize, "Expect a true value but was ${secondVersion.covers.size}")

    }

    @Test
    internal fun `can extend coverage with middle day of policy`() {

//        given
        val product = ProductsTestDataBuilder.standardCarInsurance()
        val policy = PoliciesTestBuilder.standardOneYearPolicy(LocalDate.of(2020, 1, 1))
        val newCover = product.getCovers().withCode("AC").get()

//        when:
        policy.extendCoverage(
                LocalDate.of(2020, 1, 1),
                newCover.code,
                BigDecimal.TEN,
                Period.ofDays(365)
        )

//        then:
        assertTrue(policy.policyVersions.size() == 1, "Expect a value of true but was ${policy.policyVersions.size()}")

        val firstVersion = policy.policyVersions.withNumber(1).get()

        assertTrue(firstVersion.versionStatus == PolicyVersionStatus.Active, "Expect a version status of Active but was ${firstVersion.versionStatus}" )

        val coverFirstVersionSize = firstVersion.covers.size
        val secondVersion = policy.policyVersions.withNumber(1).get()

        assertTrue(secondVersion.versionStatus == PolicyVersionStatus.Active, "Expect a Draft but was ${secondVersion.versionStatus}")
        assertTrue(secondVersion.covers.size == coverFirstVersionSize, "Expect number of covers to be 2 but was ${secondVersion.covers.size}")
    }
}



