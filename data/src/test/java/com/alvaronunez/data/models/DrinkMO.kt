package com.alvaronunez.data.models

import com.alvaronunez.domain.models.Drink
import com.alvaronunez.domain.models.Ingredient

object DrinkMO {
    fun mockedDrinkFirst() = Drink(
        name = "First Mocked Name",
        thumb = "",
        id = null
    )

    fun mockedDrinkSecond() = Drink(
        name = "Second Mocked Name",
        thumb = "",
        id = null
    )
}