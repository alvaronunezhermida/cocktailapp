package com.alvaronunez.data.models

import com.alvaronunez.domain.models.Ingredient

object IngredientMO {
    fun mockedIngredientFirst() = Ingredient(
        name = "First Mocked Name"
    )

    fun mockedIngredientSecond() = Ingredient(
        name = "Second Mocked Name"
    )
}