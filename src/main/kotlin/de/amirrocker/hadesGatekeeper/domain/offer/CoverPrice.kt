package de.amirrocker.hadesGatekeeper.domain.offer

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import de.amirrocker.hadesGatekeeper.domain.product.Cover
import java.time.Period
import java.util.*

class CoverPrice/*(
    private val id:UUID = UUID.randomUUID(),
    var coverCode:String,
    var price:MonetaryAmount,
    var coverPeriod:Period
)*/ {

    private val id:UUID = UUID.randomUUID()
    var coverCode:String
    var price:MonetaryAmount
    var coverPeriod:Period

    constructor(coverCode: String, price: MonetaryAmount, coverPeriod: Period) {
        this.coverCode = coverCode
        this.price = price
        this.coverPeriod = coverPeriod
    }

    constructor(cover:Cover, price: MonetaryAmount, coverPeriod: Period) {
        this.coverCode = cover.code
        this.price = price
        this.coverPeriod = coverPeriod
    }

}