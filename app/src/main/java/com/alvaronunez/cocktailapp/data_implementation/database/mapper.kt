package com.alvaronunez.cocktailapp.data_implementation.database

import com.alvaronunez.cocktailapp.data_implementation.database.entities.IngredientEntity
import com.alvaronunez.cocktailapp.data_implementation.models.IngredientDTO
import com.alvaronunez.domain.models.Ingredient

fun List<IngredientEntity>.toDomainIngredientList() = map { Ingredient(it.name) }

fun List<Ingredient>.toIngredientEntityList() = map { IngredientEntity(null, it.name) }