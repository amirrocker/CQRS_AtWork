package de.amirrocker.hadesGatekeeper.cqs

import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import java.time.LocalDate

class PoliciesTestBuilder {

    companion object {
        fun standardOneYearPolicy(policyStartDate:LocalDate): Policy {
            val offer = OffersTestDataBuilder.standardOneYearContractOfferValidUntil(policyStartDate.plusDays(31))
            return Policy.convertOffer(offer, policyStartDate.minusDays(2), policyStartDate)
        }
    }

}
