package com.alvaronunez.cocktailapp.data_implementation.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DrinkDTO (
    @Json(name="strDrink")
    val name: String,
    @Json(name="strDrinkThumb")
    val thumb: String,
    @Json(name="idDrink")
    val id: String?
)