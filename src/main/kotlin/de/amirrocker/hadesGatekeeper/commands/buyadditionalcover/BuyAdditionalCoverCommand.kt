package de.amirrocker.hadesGatekeeper.commands.buyadditionalcover

import de.amirrocker.hadesGatekeeper.cqs.Command
import lombok.Getter
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period
import java.util.*

@Getter
class BuyAdditionalCoverCommand : Command<BuyAdditionalCoverResult> {
    lateinit var policyId:UUID
    lateinit var effectiveDateOfChange: LocalDate
    lateinit var newCoverCode: String
    lateinit var newCoverPrice:BigDecimal
    lateinit var newCoverPriceUnit: Period
}