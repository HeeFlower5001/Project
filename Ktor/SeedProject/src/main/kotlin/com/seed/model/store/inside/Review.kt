package com.seed.model.store.inside

import com.seed.model.user.User
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val user: User?,
    var title: String?,
    var content: String?,
    var rate: Int?
) {

}