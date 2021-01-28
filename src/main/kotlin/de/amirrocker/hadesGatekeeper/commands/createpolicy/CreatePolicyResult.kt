package de.amirrocker.hadesGatekeeper.commands.createpolicy

import java.util.*

data class CreatePolicyResult(
        private val policyId:UUID,
        private val policyNumber:String
) {}