package de.amirrocker.hadesGatekeeper.domain.product

import de.amirrocker.hadesGatekeeper.domain.offer.Covers
import java.util.*
import kotlin.collections.ArrayList

class Product(
        val id:UUID,
        val code:String,
        val name:String,
        val covers:List<Cover> = ArrayList<Cover>()
) {

    fun getCovers() =
            Covers(covers)

}