package com.seed.model.user

import kotlinx.serialization.Serializable

@Serializable
object UserRepository {
    val Users = mutableListOf<User>()

    fun addUser(user: User) {
        Users.add(user)
    }

    fun addUser(id: String, pw: String, name: String, nickname: String, callNumber: String) {
        val newUser = User(id, pw, name, nickname, callNumber, 0)
        Users.add(newUser)
    }

    fun isExistId(id: String): Boolean {
        for (i in 0 until Users.size) {
            if (id == Users[i].id) {
                return true
            }
        }

        return false
    }

    fun findUser(id: String, pw: String) : User? {
        for (i in 0 until  Users.size) {
            if (id == Users[i].id && pw == Users[i].pw) {
                return Users[i]
            }
        }

        // User 찾지 못했다면 null 반환
        return null
    }
}