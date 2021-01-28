package de.amirrocker.hadesGatekeeper.commands.buyadditionalcover

import java.util.*

data class BuyAdditionalCoverResult(
    val policyId: UUID,
    val versionWithAdditionalCoverNumber: Int = 0
) {}
