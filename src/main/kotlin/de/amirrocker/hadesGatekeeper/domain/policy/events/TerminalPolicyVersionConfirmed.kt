package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.base.Event
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyVersionStatus

class TerminalPolicyVersionConfirmed(
        val versionNumber: Int
):Event<Policy>() {
    val versionStatus = PolicyVersionStatus.Active

    override fun applyOn(aggregate: Policy) {
        aggregate.apply {
            println("This policy has been apply'd with this: $this on aggregate Policy: $aggregate")
        }
    }
}
