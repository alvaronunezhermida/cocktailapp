package com.alvaronunez.data.source

import com.alvaronunez.data.Result
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.domain.models.Ingredient

interface RemoteDataSource {
    suspend fun getIngredients(): Result<List<Ingredient>>
    suspend fun getDrinksByIngredient(ingredientName: String): Result<List<Drink>>
}