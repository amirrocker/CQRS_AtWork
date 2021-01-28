package de.amirrocker.hadesGatekeeper.db

import de.amirrocker.hadesGatekeeper.domain.policy.Policy
import de.amirrocker.hadesGatekeeper.domain.policy.PolicyRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryPolicyRepository : PolicyRepository {


    val eventStore = InMemoryEventStore()

    override fun getById(uid: UUID):Policy  =
            Policy(uid, eventStore.getEventsForAggregate(uid))

    override fun save(policy: Policy, expectedVersion: Int) {
        eventStore.saveEvents(policy.id, expectedVersion.toLong(), (policy.getChanges() as ArrayList))
    }
}