package de.amirrocker.hadesGatekeeper.domain.offer

import de.amirrocker.hadesGatekeeper.domain.product.Cover
import java.util.*
import java.util.function.Predicate

data class Covers(
        val covers: List<Cover>
) {

    fun withCode(code:String):Optional<Cover> =
            covers.parallelStream()
                    .filter(Predicate {
                        it.code.equals(code)
                    })
                    .findAny()

}