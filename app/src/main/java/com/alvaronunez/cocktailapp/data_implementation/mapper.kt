package com.alvaronunez.usecases

import com.alvaronunez.cocktailapp.data_implementation.models.DrinkDTO
import com.alvaronunez.cocktailapp.data_implementation.models.IngredientDTO
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.domain.models.Ingredient

fun List<IngredientDTO>.toIngredientsList() = map { Ingredient(it.name) }
fun List<DrinkDTO>.toDrinksList() = map { Drink(it.name, it.thumb, it.id) }