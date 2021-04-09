package de.amirrocker.hadesGatekeeper.domain.common

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class ValueAmount(
        private val amount: BigDecimal?
) {

    companion object {

        fun zero(): ValueAmount {
            return from(BigDecimal("0.00"))
        }

        fun from(amount: BigDecimal?): ValueAmount {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(amount)
        }

        fun from(amount: String?): ValueAmount {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(BigDecimal(amount))
        }

        fun from(amount: Int?): ValueAmount {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(BigDecimal(amount))
        }
    }

    fun add(valueAmount: ValueAmount?): ValueAmount {
        if (valueAmount == null) {
            throw RuntimeException("Cant save null ValueAmount")
        }
        return ValueAmount(amount?.add(valueAmount.toBigDecimal()))
    }

    fun subtract(valueAmount: ValueAmount?): ValueAmount {
        if (valueAmount == null) {
            throw RuntimeException("Cant subtract null ValueAmount")
        }
        return ValueAmount(amount?.subtract(valueAmount.toBigDecimal()))
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


    fun greaterThan(valueAmount: ValueAmount): Boolean =
            this.compareTo(valueAmount) == 1

    /* TODO Check and find Better solution than !! */
    fun greaterOrEqual(valueAmount: ValueAmount): Boolean =
            this.compareTo(valueAmount) >= 0


    fun lowerThan(valueAmount: ValueAmount): Boolean =
            this.compareTo(valueAmount) == -1


    fun lowerOrEqual(valueAmount: ValueAmount): Boolean =
            this.compareTo(valueAmount) <= 0


    fun equalTo(valueAmount: ValueAmount): Boolean =
            this.compareTo(valueAmount) == 0


    fun toWholeNumber(): ValueAmount =
            ValueAmount(amount?.setScale(0, RoundingMode.HALF_UP))


    fun round(numerOfDecimalPlaces: Int): ValueAmount =
            ValueAmount(amount?.setScale(numerOfDecimalPlaces, RoundingMode.HALF_UP))


    fun min(first: ValueAmount, second: ValueAmount): ValueAmount =
            if (first.compareTo(second) < 0) first else second


    fun max(first: ValueAmount, second: ValueAmount): ValueAmount =
            if (first.compareTo(second) >= 0) first else second


    fun multiply(multiplier: BigDecimal?): ValueAmount =
            ValueAmount(amount?.multiply(multiplier))


    fun multiply(multiplier: Int): ValueAmount =
            ValueAmount(amount?.multiply(BigDecimal.valueOf(multiplier.toLong())))


    fun multiply(multiplier: BigDecimal?, rounding: RoundingMode?): ValueAmount {
        val multiplication: BigDecimal? = amount?.multiply(multiplier)
        return ValueAmount(multiplication?.setScale(2, rounding))
    }

    fun toBigDecimal(): BigDecimal =
            BigDecimal(amount.toString()) // amount;


    operator fun compareTo(o: ValueAmount): Int =
            amount?.compareTo(o.amount)!!


    override fun equals(other: Any?): Boolean {
        return if (other !is ValueAmount) {
            false
        } else amount == other.toBigDecimal()
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