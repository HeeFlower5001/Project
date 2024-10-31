package com.seed.model.user

import kotlinx.serialization.Serializable
// 토큰 방식을 사용해도 메소드를 추가해야되는건가?

@Serializable
data class User(
    val id: String?,
    var pw: String?,
    var name: String?,
    var nickname: String?,
    var callNumber: String?, //Int 로 변경 가능
    var countNoShow: Int = 0
) {
    fun incrementCountNoShow() {
        countNoShow++
    }

    fun decrementCountNoShow() {
        countNoShow--
    }
}
