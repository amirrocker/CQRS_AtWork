package de.amirrocker.hadesGatekeeper.domain.policy

import de.amirrocker.hadesGatekeeper.domain.common.MonetaryAmount
import java.time.Period

data class UnitPrice(
        val price:MonetaryAmount,
        val pricePeriod: Period
) {}
