package de.amirrocker.hadesGatekeeper.init

import de.amirrocker.hadesGatekeeper.domain.common.Person

class InitPersonBuilder {
    companion object {
        fun isnogood() =
                Person(
                        "wesir",
                        "isnogood",
                        "paysNoTaxIsHisId"
                )
    }
}
