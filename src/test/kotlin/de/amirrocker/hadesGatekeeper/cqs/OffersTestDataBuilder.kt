package de.amirrocker.hadesGatekeeper.cqs

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.offer.Offer
import de.amirrocker.hadesGatekeeper.domain.product.Cover
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.HashMap

class OffersTestDataBuilder {

    companion object {
        fun standardOneYearContractOfferValidUntil(theDate: LocalDate):Offer? {
            println("standardOneYearContractOfferValidUntil called")
            val product = ProductsTestDataBuilder.standardCarInsurance()
            val coversPrices = HashMap<Cover, MonetaryAmount>()
            MonetaryAmount.from("500")?.let {
                coversPrices.put(
                        product.getCovers().withCode("OC").get(),
                        it
                )
            }
            return MonetaryAmount.from("500")?.let {
                Offer(
                        UUID.randomUUID(),
                        "1",
                        product,
                        PersonsTestDataBuilder.MisterIsnogood(),
                        PersonsTestDataBuilder.MisterIsnogood(),
                        CarsTestDataBuilder.oldOpelManta(),
                        Period.ofDays(365),
                        it,
                        theDate.minusDays(30),
                        coversPrices
                )
            }
        }
    }

}