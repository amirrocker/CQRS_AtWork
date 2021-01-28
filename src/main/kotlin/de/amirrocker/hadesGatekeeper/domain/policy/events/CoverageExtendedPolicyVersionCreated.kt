package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.base.Event
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyCover
import java.time.LocalDate

class CoverageExtendedPolicyVersionCreated(
        val versionNumber: Int,
        val baseVersionNumber: Int,
        val versionFrom: LocalDate,
        val versionTo: LocalDate,
        var pNewCover: PolicyCover?):Event<Policy>() {

    var newCover: PolicyEventsData.PolicyCoverData? = pNewCover?.let { PolicyEventsData.PolicyCoverData.from(it) }

    override fun applyOn(aggregate:Policy) {
        aggregate.apply {
            println("This policy has been apply'd with this: $this on aggregate Policy: $aggregate")
        }
    }

}
