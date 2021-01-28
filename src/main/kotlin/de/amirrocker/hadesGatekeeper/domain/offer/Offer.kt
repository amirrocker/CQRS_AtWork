package de.amirrocker.hadesGatekeeper.domain.offer

import de.amirrocker.hadesGatekeeper.domain.common.Car
import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.common.Person
import de.amirrocker.hadesGatekeeper.domain.product.Cover
import de.amirrocker.hadesGatekeeper.domain.product.Product
import java.time.LocalDate
import java.time.Period
import java.util.*
import java.util.function.BiConsumer
import kotlin.collections.ArrayList

data class Offer(
        val id:UUID,
        val number:String,
        val product:Product,
        val customer:Person,
        val driver:Person,
        val car:Car,
        val coverPeriod:Period,
        val totalCost:MonetaryAmount,
        val creationDate:LocalDate,
        val coversPrices:Map<Cover, MonetaryAmount>
) {

    var status:OfferStatus = OfferStatus.New
    var productCode: String
    val covers: List<CoverPrice> = mutableListOf()

    init {
        productCode = product.code
        coversPrices.forEach(BiConsumer { key, value ->
            (covers as ArrayList).add(
                    CoverPrice(
                        key,
                        value,
                        coverPeriod
                    )
            )
        })
    }

    enum class OfferStatus {
        New, Converted, Rejected
    }

    fun isConverted(): Boolean {
        return OfferStatus.Converted == status
    }

    /*
    * TODO check whether the return structure like this works
    * */
    fun isExpired(theDate: LocalDate?): Boolean {
        getValidityDate()?.let {
            return it.isBefore(theDate)
        }
        return false
    }

    fun isRejected(): Boolean {
        return OfferStatus.Rejected == status
    }

    private fun getValidityDate(): LocalDate? {
        return creationDate?.plusDays(30)
    }

}