package com.alvaronunez.data.source

import com.alvaronunez.data.Result
import com.alvaronunez.domain.models.Ingredient

interface LocalDataSource {
    suspend fun isIngredientsListEmpty(): Boolean
    suspend fun getIngredients(): Result<List<Ingredient>>
    suspend fun saveIngredients(ingredients: List<Ingredient>): Result<Boolean>
}