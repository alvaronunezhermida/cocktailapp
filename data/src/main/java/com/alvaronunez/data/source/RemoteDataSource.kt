package com.alvaronunez.data.source

import com.alvaronunez.data.Result
import com.alvaronunez.data.models.DrinkDTO
import com.alvaronunez.data.models.IngredientDTO

interface RemoteDataSource {
    suspend fun getIngredients(): Result<List<IngredientDTO>>
    suspend fun getDrinksByIngredient(ingredientName: String): Result<List<DrinkDTO>>
}