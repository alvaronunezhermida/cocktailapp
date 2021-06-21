package com.alvaronunez.cocktailapp.data_implementation.database

import com.alvaronunez.cocktailapp.data_implementation.database.entities.IngredientEntity
import com.alvaronunez.data.models.IngredientDTO

fun List<IngredientEntity>.toIngredientDTOList() = map { IngredientDTO(it.name) }

fun List<IngredientDTO>.toIngredientEntityList() = map { IngredientEntity(null, it.name) }