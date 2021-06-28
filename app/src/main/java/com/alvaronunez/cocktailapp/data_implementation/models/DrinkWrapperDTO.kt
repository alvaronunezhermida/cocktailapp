package com.alvaronunez.cocktailapp.data_implementation.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DrinkWrapperDTO (
    @Json(name="drinks")
    val list: List<DrinkDTO>
)