package de.amirrocker.hadesGatekeeper.commands.createpolicy

import de.amirrocker.hadesGatekeeper.cqs.Command
import java.time.LocalDate

data class CreatePolicyCommand(
        val offerNumber:String,
        val purchaseDate:LocalDate,
        val policyStartDate:LocalDate
) : Command<CreatePolicyResult> {
}