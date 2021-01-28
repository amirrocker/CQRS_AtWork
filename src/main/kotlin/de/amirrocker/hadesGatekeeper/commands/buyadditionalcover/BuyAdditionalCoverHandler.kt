package de.amirrocker.hadesGatekeeper.commands.buyadditionalcover

import de.amirrocker.hadesGatekeeper.cqs.Command
import de.amirrocker.hadesGatekeeper.cqs.CommandHandler
import de.amirrocker.hadesGatekeeper.domain.base.AggregateRoot
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class BuyAdditionalCoverHandler(
        val policyRepository: PolicyRepository
): CommandHandler<BuyAdditionalCoverResult, BuyAdditionalCoverCommand> {

    override fun handle(command: BuyAdditionalCoverCommand): BuyAdditionalCoverResult {
        val policy: Policy = policyRepository.getById(command.policyId)
        policy.extendCoverage(
                command.effectiveDateOfChange,
                command.newCoverCode,
                command.newCoverPrice,
                command.newCoverPriceUnit
        )
        /* TODO why can I not access version even though lombok exposed the private property with the @Getter annotation ? */
        policyRepository.save(policy, (policy as AggregateRoot).version )

        return BuyAdditionalCoverResult(
            policy.id,
                policy.policyVersions.last().versionNumber
        )
    }
}