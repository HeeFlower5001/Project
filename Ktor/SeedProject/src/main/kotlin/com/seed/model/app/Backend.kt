package com.seed.model.app

import com.seed.model.user.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val id: String, val pw: String)

@Serializable
data class LoginResponse(val message: String, val user: User)

/*
class TimeUpdate {
    fun start() {
        GlobalScope.launch {
            while (true) {
                delay(1000)

            }
        }
    }
}
 */