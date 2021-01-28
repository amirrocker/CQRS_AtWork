package de.amirrocker.hadesGatekeeper.domain.policy

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS


data class DateRange(
        val from:LocalDate,
        val to:LocalDate
) {
    companion object {
        fun between(from: LocalDate, to:LocalDate) =
                DateRange(from, to)
    }

    fun endOn(endDate:LocalDate) =
            DateRange(from, endDate)

    fun days() =
            DAYS.between(from, to)

    fun contains(eventDate:LocalDate): Boolean {
        if(eventDate.isAfter(to)) return false
        if(eventDate.isBefore(from)) return false
        return true
    }

}
