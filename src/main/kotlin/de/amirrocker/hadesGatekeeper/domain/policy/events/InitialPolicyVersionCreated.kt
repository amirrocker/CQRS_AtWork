package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.base.Event
import de.amirrocker.hadesGatekeeper.domain.common.Car
import de.amirrocker.hadesGatekeeper.domain.common.Person
import de.amirrocker.hadesGatekeeper.domain.policy.DateRange
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyCover
import java.time.LocalDate
import java.util.stream.Collectors

data class InitialPolicyVersionCreated(
        val policyNumber: String,
        val productCode: String?,
        val coverPeriod: DateRange,
        val purchaseDate: LocalDate,
        val pPolicyHolder: Person,
        var pCar: Car,
        var covers: List<PolicyCover>) : Event<Policy>()
{

    private var policyHolder: PolicyEventsData.PersonData
    private lateinit var car: PolicyEventsData.CarData

    val coverFrom : LocalDate = coverPeriod.from
    val coverTo : LocalDate = coverPeriod.to
//    val policyHolder = PolicyEventsData.PersonData(policyHolder.firstName, policyHolder.lastName, policyHolder.taxId)

    init {

        policyHolder = PolicyEventsData.PersonData(pPolicyHolder.firstName, pPolicyHolder.lastName, pPolicyHolder.taxId)
        car = PolicyEventsData.CarData(pCar.make, pCar.plateNumber, pCar.productionYear)
        val covers: List<PolicyEventsData.PolicyCoverData>  = covers.parallelStream()
                .map {
                    PolicyEventsData.PolicyCoverData.from(it)
                }
                .collect(Collectors.toUnmodifiableList())
    }


    override fun applyOn(aggregate: Policy) {
        aggregate.apply(this)
    }
}
