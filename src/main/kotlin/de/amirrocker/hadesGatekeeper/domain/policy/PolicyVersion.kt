package de.amirrocker.hadesGatekeeper.domain.policy

import de.amirrocker.hadesGatekeeper.domain.common.BusinessException
import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import java.time.LocalDate
import java.util.function.Predicate

data class PolicyVersion(
        val versionNumber:Int,
        var versionStatus:PolicyVersionStatus,
        private val policyStatus: PolicyStatus,
        private val coverPeriod: DateRange,
        private val versionPeriod: DateRange,
        val covers: List<PolicyCover>
) : PolicyState {

    companion object {
        fun of( versionNumber:Int,
                versionStatus:PolicyVersionStatus,
                policyStatus: PolicyStatus,
                coverPeriod: DateRange,
                versionPeriod: DateRange,
                covers: List<PolicyCover>) = PolicyVersion(
                    versionNumber,
                    versionStatus,
                    policyStatus,
                    coverPeriod,
                    versionPeriod,
                    covers
                )

    }

    fun recalculateTotal(): MonetaryAmount =
            covers.parallelStream()
                    .map(PolicyCover::amount)
                    .reduce(MonetaryAmount.zero(), MonetaryAmount::add )

    fun addCover(coverCode:String, price: UnitPrice, coverPeriod: DateRange) {
        if(!isDraft()) throw BusinessException("Cannot modify draft .... ")
        (covers as ArrayList).add(PolicyCover(coverCode = coverCode, coverPeriod = coverPeriod, price = price))
    }

    fun isDraft():Boolean =
            PolicyVersionStatus.Draft.equals(versionStatus)

    fun isActive() =
            PolicyVersionStatus.Active.equals(versionStatus)

    fun coversDate(theDate: LocalDate) =
            coverPeriod.contains(theDate)

    fun isEffectiveOn(theDate:LocalDate) =
            versionPeriod.contains(theDate)

    override fun getPolicyStatus(): PolicyStatus = policyStatus

    override fun getCoverPeriod(): DateRange = coverPeriod

    override fun getVersionPeriod(): DateRange = versionPeriod

    override fun getCovers(): Collection<PolicyCover> = covers

    fun confirm() {
        if(!isDraft()) throw BusinessException("Only Draft version can be confirmed")
        this.versionStatus = PolicyVersionStatus.Active
    }

    fun createDraftCopy(newVersionNumber:Int, versionPeriod:DateRange) =
            PolicyVersion(
                    newVersionNumber,
                    PolicyVersionStatus.Draft,
                    PolicyStatus.Active,
                    coverPeriod,
                    versionPeriod,
                    ArrayList(covers)
            )

    fun containsCover(coverCode:String) =
            covers.parallelStream()
                    .anyMatch(Predicate {
                        it.equals(coverCode)
                    })

}