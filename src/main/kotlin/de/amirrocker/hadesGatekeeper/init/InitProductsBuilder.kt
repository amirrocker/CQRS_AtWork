package de.amirrocker.hadesGatekeeper.init

import de.amirrocker.hadesGatekeeper.domain.product.Cover
import de.amirrocker.hadesGatekeeper.domain.product.Product
import java.util.*

class InitProductsBuilder {

    companion object {
        fun standardCarInsurance():Product =
                Product(
                        UUID.randomUUID(),
                        "STD_CAR_INSURANCE",
                        "standard car insurance package",
                        listOf(
                                Cover(UUID.randomUUID(), "OC", "Third Party Liability"),
                                Cover(UUID.randomUUID(), "AC", "Auto Casco / Vollkasko"),
                                Cover(UUID.randomUUID(), "ASSIST", "Assistance")
                        )
                )
    }

}