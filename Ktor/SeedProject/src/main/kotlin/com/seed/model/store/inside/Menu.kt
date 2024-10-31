package com.seed.model.store.inside

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    var name: String?,
    var price: Int?,
    var description: String?
    // image: Png
)
{
}