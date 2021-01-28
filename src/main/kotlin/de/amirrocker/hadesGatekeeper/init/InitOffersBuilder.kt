package de.amirrocker.hadesGatekeeper.init

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.offer.Offer
import de.amirrocker.hadesGatekeeper.domain.product.Cover
import de.amirrocker.hadesGatekeeper.domain.product.Product
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.HashMap

class InitOffersBuilder {

    companion object {

        fun standardOneYearOCOfferValidUntil(product:Product, number:String, validityEnd:LocalDate) =
                Offer(
                     UUID.randomUUID(),
                        number,
                        product,
                        InitPersonBuilder.isnogood(),
                        InitPersonBuilder.isnogood(),
                        InitCarsBuilder.oldMercedesBenz(),
                        Period.ofDays(365),
                        MonetaryAmount.Companion.from(500)!!,
                        validityEnd.minusDays(30),
                        createCoversPrices(product)
                )

        private fun createCoversPrices(product: Product): Map<Cover, MonetaryAmount> {
            val coversPrices = HashMap<Cover, MonetaryAmount>()
            coversPrices.put(product.getCovers().withCode("AC").get(), MonetaryAmount.Companion.from(500)!!)
            return coversPrices
        }

    }

}