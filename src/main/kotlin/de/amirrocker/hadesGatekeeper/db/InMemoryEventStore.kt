package de.amirrocker.hadesGatekeeper.db

import de.amirrocker.hadesGatekeeper.domain.base.Event
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Function
import kotlin.collections.ArrayList

class InMemoryEventStore : EventStore {

    private var events = ConcurrentHashMap<UUID, List<Event<*>>>()

    override fun getEventsForAggregate(aggregateId: UUID): List<Event<*>> =
            events.get(aggregateId).orEmpty()

    override fun saveEvents(aggregateId: UUID, version: Long, events: ArrayList<Event<*>>) {
        val list = this.events.computeIfAbsent(aggregateId) { ArrayList() }
        (list as ArrayList).addAll(events)
    }
}