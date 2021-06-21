package com.alvaronunez.usecases

import com.alvaronunez.data.models.DrinkDTO
import com.alvaronunez.data.models.IngredientDTO
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.domain.models.Ingredient

fun List<IngredientDTO>.toIngredientsList() = map { Ingredient(it.name) }
fun List<DrinkDTO>.toDrinksList() = map { Drink(it.name) }