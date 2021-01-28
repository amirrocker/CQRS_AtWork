package de.amirrocker.hadesGatekeeper.cqs

import de.amirrocker.hadesGatekeeper.domain.product.Cover
import de.amirrocker.hadesGatekeeper.domain.product.Product
import java.util.*

class ProductsTestDataBuilder {
    companion object {
        fun standardCarInsurance() =
                Product(
                        UUID.randomUUID(),
                        "STD_CAR_INSURANCE",
                        "Standard Car Insurance",
                        listOf(
                                Cover(UUID.randomUUID(), "OC", "Third Party Liability"),
                                Cover(UUID.randomUUID(), "AC", "Auto casco"),
                                Cover(UUID.randomUUID(), "ASSISTANCE", "Assistance")
                        )
                )





    }
}
