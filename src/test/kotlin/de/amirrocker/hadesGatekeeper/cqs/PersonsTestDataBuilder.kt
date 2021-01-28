package de.amirrocker.hadesGatekeeper.cqs

import de.amirrocker.hadesGatekeeper.domain.common.Person

class PersonsTestDataBuilder {
    companion object {
        fun MisterIsnogood() =
                Person("Grosswesir", "Isnogood", "MyOwnTaxId")


        fun MadameAlotta() {
            Person("Allotta", "Applecake", "ADifferentTaxId")
        }
    }
}
