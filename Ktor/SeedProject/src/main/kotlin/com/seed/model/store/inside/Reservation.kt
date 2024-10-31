package com.seed.model.store.inside

import com.seed.model.user.User
import kotlinx.serialization.Serializable
import kotlinx.datetime.*

@Serializable
data class Reservation(
    val user: User?,
    val numberOfPeople: Int?,
    val whatTime: LocalDateTime?,
    val whereTable: Int?,
    ) {
    // 함수 고치기
    fun createWhatTime(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        // whatTime = LocalDateTime(year, month, day, hour, minute)
    }
}