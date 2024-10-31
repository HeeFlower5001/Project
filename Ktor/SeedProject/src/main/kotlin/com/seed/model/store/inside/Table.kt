package com.seed.model.store.inside

import kotlinx.serialization.Serializable
import kotlinx.datetime.*
import kotlin.time.*

@Serializable
data class Table (
    var seat: Int?,
    var reservationAvailable: Boolean = true,
) {
    var time: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        get () {
            if (!reservationAvailable) {
                UpdateTimeConsuming()
            }

            return field
        }

    fun initTime() {
        time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun UpdateTimeConsuming() :Duration {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val duration: Duration = time.toInstant(TimeZone.currentSystemDefault()) - now.toInstant(TimeZone.currentSystemDefault())

        return duration
    }

    fun toggleReservationAvailable() {
        reservationAvailable = !reservationAvailable
        initTime()
    }
}

