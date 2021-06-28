package com.alvaronunez.cocktailapp.data_implementation.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class IngredientWrapperDTO (
    @Json(name="drinks")
    val list: List<IngredientDTO>
)