package de.amirrocker.hadesGatekeeper.db

import de.amirrocker.hadesGatekeeper.domain.base.Event
import java.util.*

interface EventStore {

    fun getEventsForAggregate(aggregateId:UUID):List<Event<*>>

    fun saveEvents(aggregateId:UUID, version:Long, events:ArrayList<Event<*>>)

}