package com.alvaronunez.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientDTO (
    @Json(name="strIngredient1")
    val name: String
)