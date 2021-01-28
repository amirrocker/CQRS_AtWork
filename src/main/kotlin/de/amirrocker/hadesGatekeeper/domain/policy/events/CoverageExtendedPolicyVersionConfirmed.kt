package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.base.Event
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyVersionStatus

class CoverageExtendedPolicyVersionConfirmed(
        val versionNumber:Int,
        val versionStatus: PolicyVersionStatus
) : Event<Policy>() {

    override fun applyOn(aggregate: Policy) {
        aggregate.apply {
            println("This policy has been apply'd with this: $this on aggregate Policy: $aggregate")
        }
    }
}