package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyCover
import java.time.LocalDate
import java.time.Period

sealed class PolicyEventsData {

    data class PersonData(
        val firstName: String,
        val lastName: String,
        val taxId: String
    ){}

    data class CarData(
            val make:String,
            val plateNumber:String,
            val productionYear:Int
    ) {}

    data class PolicyCoverData(
            val code:String,
            val coverFrom:LocalDate,
            val coverTo:LocalDate,
            val amount:MonetaryAmount,
            val price:MonetaryAmount,
            val priceUnit:Period
    ) {
        companion object {
            fun from(policyCover: PolicyCover): PolicyCoverData =
                    PolicyCoverData(
                            policyCover.coverCode,
                            policyCover.coverPeriod.from,
                            policyCover.coverPeriod.to,
                            policyCover.amount,
                            policyCover.price.price,
                            policyCover.price.pricePeriod
                    )
        }
    }

}
