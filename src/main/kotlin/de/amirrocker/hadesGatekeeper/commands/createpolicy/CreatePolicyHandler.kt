package de.amirrocker.hadesGatekeeper.commands.createpolicy

import de.amirrocker.hadesGatekeeper.domain.offer.OfferRepository
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyRepository
import de.amirrocker.hadesGatekeeper.cqs.Command
import de.amirrocker.hadesGatekeeper.cqs.CommandHandler

class CreatePolicyHandler(
        val offerRepository:OfferRepository,
        val policyRepository:PolicyRepository
) : CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

//    override fun handle(command: Command): CreatePolicyResult {
//        val offer = offerRepository.withNumber((command as CreatePolicyCommand).offerNumber)
//
//        val policy = Policy.convertOffer(offer, command.purchaseDate, command.policyStartDate)
//        policyRepository.save(policy, -1)
//
//        return CreatePolicyResult(policy.id, policy.number)
//    }

    override fun handle(command: CreatePolicyCommand): CreatePolicyResult {
        val offer = offerRepository.withNumber((command as CreatePolicyCommand).offerNumber)

        val policy = Policy.convertOffer(offer, command.purchaseDate, command.policyStartDate)
        policyRepository.save(policy, -1)

        return CreatePolicyResult(policy.id, policy.number)
    }
}