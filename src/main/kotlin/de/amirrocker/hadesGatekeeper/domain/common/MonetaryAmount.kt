package de.amirrocker.hadesGatekeeper.domain.common

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class MonetaryAmount(
        private val amount: BigDecimal?
) {

//    fun zero(): MonetaryAmount? {
//        return from(BigDecimal("0.00"))
//    }
//
//    fun from(amount: BigDecimal?): MonetaryAmount {
//        if (amount == null) {
//            throw RuntimeException("Amount for MonetaryAmount cannot be null")
//        }
//        return MonetaryAmount(amount)
//    }
//
//    fun from(amount: String?): MonetaryAmount {
//        if (amount == null) {
//            throw RuntimeException("Amount for MonetaryAmount cannot be null")
//        }
//        return MonetaryAmount(BigDecimal(amount))
//    }
//
//    fun from(amount: Int?): MonetaryAmount {
//        if (amount == null) {
//            throw RuntimeException("Amount for MonetaryAmount cannot be null")
//        }
//        return MonetaryAmount(BigDecimal(amount))
//    }
//
//    fun add(monetaryAmount: MonetaryAmount?): MonetaryAmount {
//        if (monetaryAmount == null) {
//            throw RuntimeException("Cant save null MonetaryAmount")
//        }
//        return MonetaryAmount(amount?.add(monetaryAmount.toBigDecimal()))
//    }
//
//    fun subtract(monetaryAmount: MonetaryAmount?): MonetaryAmount {
//        if (monetaryAmount == null) {
//            throw RuntimeException("Cant subtract null MonetaryAmount")
//        }
//        return MonetaryAmount(amount?.subtract(monetaryAmount.toBigDecimal()))
//    }
//
//    fun isPositive(): Boolean {
//        return amount!!.signum() == 1
//    }
//
//    fun isNegative(): Boolean {
//        return amount!!.signum() == -1
//    }
//
//    fun isZero(): Boolean {
//        return amount!!.signum() == 0
//    }
//
//    fun isPositiveOrZero(): Boolean {
//        return isPositive() || isZero()
//    }
//
//    fun isNegativeOrZero(): Boolean {
//        return isNegative() || isZero()
//    }
//
//    fun greaterThan(monetaryAmount: MonetaryAmount): Boolean {
//        return this.compareTo(monetaryAmount) == 1
//    }
//
//    fun greaterOrEqual(monetaryAmount: MonetaryAmount): Boolean {
//        return this.compareTo(monetaryAmount) >= 0
//    }
//
//    fun lowerThan(monetaryAmount: MonetaryAmount): Boolean {
//        return this.compareTo(monetaryAmount) == -1
//    }
//
//    fun lowerOrEqual(monetaryAmount: MonetaryAmount): Boolean {
//        return this.compareTo(monetaryAmount) <= 0
//    }
//
//    fun equalTo(monetaryAmount: MonetaryAmount): Boolean {
//        return this.compareTo(monetaryAmount) == 0
//    }
//
//    fun toWholeNumber(): MonetaryAmount {
//        return MonetaryAmount(amount?.setScale(0, RoundingMode.HALF_UP))
//    }
//
//    fun round(numerOfDecimalPlaces: Int): MonetaryAmount {
//        return MonetaryAmount(amount?.setScale(numerOfDecimalPlaces, RoundingMode.HALF_UP))
//    }
//
//    fun min(first: MonetaryAmount, second: MonetaryAmount): MonetaryAmount {
//        return if (first.compareTo(second) < 0) first else second
//    }
//
//    fun max(first: MonetaryAmount, second: MonetaryAmount): MonetaryAmount {
//        return if (first.compareTo(second) >= 0) first else second
//    }
//
//    fun multiply(multiplier: BigDecimal?): MonetaryAmount {
//        return MonetaryAmount(amount?.multiply(multiplier))
//    }
//
//    fun multiply(multiplier: Int): MonetaryAmount {
//        return MonetaryAmount(
//                amount?.multiply(
//                        BigDecimal.valueOf(
//                                multiplier.toLong()
//                        )
//                )
//        )
//    }
//
//    fun multiply(multiplier: BigDecimal?, rounding: RoundingMode?): MonetaryAmount? {
//        val multiplication = amount!!.multiply(multiplier)
//        return MonetaryAmount(multiplication.setScale(2, rounding))
//    }
//
//    fun toBigDecimal(): BigDecimal {
//        return BigDecimal(amount.toString()) // amount;
//    }
//
//    operator fun compareTo(o: MonetaryAmount): Int {
//        amount?.let {
//            return it.compareTo(o.amount)
//        }
//        return -9999
//    }
//
//    override fun equals(`object`: Any?): Boolean {
//        return if (`object` !is MonetaryAmount) {
//            false
//        } else amount == `object`.toBigDecimal()
//    }
//
//    override fun hashCode(): Int {
//        var hash = 17
//        hash = hash * 29 + amount.hashCode()
//        return hash
//    }
//
//    override fun toString(): String {
//        val df = DecimalFormat()
//        df.maximumFractionDigits = 2
//        df.minimumFractionDigits = 0
//        df.isGroupingUsed = false
//        return df.format(amount)
//    }


//    private val amount: BigDecimal? = null

    companion object {

        fun zero(): MonetaryAmount? {
            return from(BigDecimal("0.00"))
        }

        fun from(amount: BigDecimal?): MonetaryAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for MonetaryAmount cannot be null")
            }
            return MonetaryAmount(amount)
        }

        fun from(amount: String?): MonetaryAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for MonetaryAmount cannot be null")
            }
            return MonetaryAmount(BigDecimal(amount))
        }

        fun from(amount: Int?): MonetaryAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for MonetaryAmount cannot be null")
            }
            return MonetaryAmount(BigDecimal(amount))
        }


    }



//    protected fun MonetaryAmount() {
//        this.amount = BigDecimal.ZERO
//    }

    fun add(monetaryAmount: MonetaryAmount?): MonetaryAmount? {
        if (monetaryAmount == null) {
            throw RuntimeException("Cant save null MonetaryAmount")
        }
        return MonetaryAmount(amount?.add(monetaryAmount.toBigDecimal()))
    }

    fun subtract(monetaryAmount: MonetaryAmount?): MonetaryAmount? {
        if (monetaryAmount == null) {
            throw RuntimeException("Cant subtract null MonetaryAmount")
        }
        return MonetaryAmount(amount?.subtract(monetaryAmount.toBigDecimal()))
    }

    fun isPositive(): Boolean {
        return amount?.signum() == 1
    }

    fun isNegative(): Boolean {
        return amount?.signum() == -1
    }

    fun isZero(): Boolean = amount?.signum() == 0


    fun isPositiveOrZero(): Boolean = isPositive() || isZero()


    fun isNegativeOrZero(): Boolean = isNegative() || isZero()


    fun greaterThan(monetaryAmount: MonetaryAmount): Boolean =
            this.compareTo(monetaryAmount) == 1

    /* TODO Check and find Better solution than !! */
    fun greaterOrEqual(monetaryAmount: MonetaryAmount): Boolean =
            this.compareTo(monetaryAmount)!! >= 0


    fun lowerThan(monetaryAmount: MonetaryAmount): Boolean =
            this.compareTo(monetaryAmount) == -1


    fun lowerOrEqual(monetaryAmount: MonetaryAmount): Boolean =
            this.compareTo(monetaryAmount)!! <= 0


    fun equalTo(monetaryAmount: MonetaryAmount): Boolean =
            this.compareTo(monetaryAmount) == 0


    fun toWholeNumber(): MonetaryAmount? =
            MonetaryAmount(amount?.setScale(0, RoundingMode.HALF_UP))


    fun round(numerOfDecimalPlaces: Int): MonetaryAmount? =
            MonetaryAmount(amount?.setScale(numerOfDecimalPlaces, RoundingMode.HALF_UP))


    fun min(first: MonetaryAmount, second: MonetaryAmount): MonetaryAmount? =
            if (first.compareTo(second)!! < 0) first else second


    fun max(first: MonetaryAmount, second: MonetaryAmount): MonetaryAmount? =
            if (first.compareTo(second)!! >= 0) first else second


    fun multiply(multiplier: BigDecimal?): MonetaryAmount? =
            MonetaryAmount(amount?.multiply(multiplier))


    fun multiply(multiplier: Int): MonetaryAmount? =
            MonetaryAmount(amount?.let { it.multiply(BigDecimal.valueOf(multiplier.toLong())) })


    fun multiply(multiplier: BigDecimal?, rounding: RoundingMode?): MonetaryAmount? {
        val multiplication: BigDecimal? = amount?.let { it.multiply(multiplier) }
        return MonetaryAmount(multiplication?.let { it.setScale(2, rounding) })
    }

    fun toBigDecimal(): BigDecimal =
            BigDecimal(amount.toString()) // amount;


    operator fun compareTo(o: MonetaryAmount): Int =
            amount?.let { it.compareTo(o.amount) }!!


    override fun equals(`object`: Any?): Boolean {
        return if (`object` !is MonetaryAmount) {
            false
        } else amount == `object`.toBigDecimal()
    }

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 29 + amount.hashCode()
        return hash
    }

    override fun toString(): String {
        val df = DecimalFormat()
        df.maximumFractionDigits = 2
        df.minimumFractionDigits = 0
        df.isGroupingUsed = false
        return df.format(this.amount)
    }



}