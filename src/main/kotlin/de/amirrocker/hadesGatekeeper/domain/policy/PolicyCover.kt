package de.amirrocker.hadesGatekeeper.domain.policy

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.offer.CoverPrice
import java.math.BigDecimal
import java.time.LocalDate

data class PolicyCover(
        val coverCode:String,
        val coverPeriod: DateRange,
        val price:UnitPrice
) {

    var amount:MonetaryAmount

    init {
        amount = calculateAmount()
    }

    companion object {

        fun of(coverCode:String,
               coverPeriod: DateRange,
               price:UnitPrice) = PolicyCover(
                                            coverPeriod = coverPeriod,
                                            coverCode = coverCode,
                                            price = price)



        fun forPrice(coverPrice: CoverPrice, coverPeriod: DateRange): PolicyCover =
                PolicyCover(
                        coverPeriod = coverPeriod,
                        coverCode = coverPrice.coverCode,
                        price = UnitPrice(coverPrice.price, coverPrice.coverPeriod)
                )
    }

    fun endOn(lastDayOfCover:LocalDate) =
            PolicyCover(coverCode, coverPeriod, price)

    private fun calculateAmount():MonetaryAmount {
        val coverPeriodInDays = BigDecimal.valueOf(coverPeriod.days())
        val pricePeriodInDays = BigDecimal.valueOf(price.pricePeriod.days.toDouble())
        val multiplier = coverPeriodInDays.divide(pricePeriodInDays)
        return price.price.multiply(multiplier).let { it!! }
    }



}
