package com.alvaronunez.data.source

import com.alvaronunez.data.Result
import com.alvaronunez.data.models.IngredientDTO

interface LocalDataSource {
    suspend fun isIngredientsListEmpty(): Boolean
    suspend fun getIngredients(): Result<List<IngredientDTO>>
    suspend fun saveIngredients(ingredients: List<IngredientDTO>): Result<Boolean>
}