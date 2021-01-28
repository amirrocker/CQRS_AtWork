package de.amirrocker.hadesGatekeeper.domain.policy.events

import de.amirrocker.hadesGatekeeper.domain.base.Event
import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyCover
import org.springframework.hateoas.server.mvc.afford
import java.time.LocalDate
import java.util.stream.Collectors

class TerminalPolicyVersionCreated(
        val versionNumber: Int,
        val baseVersionNumber: Int,
        val pCovers: List<PolicyCover>,
        val versionFrom: LocalDate,
        val versionTo: LocalDate,
        val coverFrom: LocalDate,
        val coverTo: LocalDate
): Event<Policy>() {

    var covers:List<PolicyEventsData.PolicyCoverData> = pCovers.parallelStream()
            .map {
                PolicyEventsData.PolicyCoverData.from(it)
            }
            .collect(Collectors.toList())

    override fun applyOn(aggregate: Policy) {
        aggregate.apply {
            println("This policy has been apply'd with this: $this on aggregate Policy: $aggregate")
        }
    }
}
