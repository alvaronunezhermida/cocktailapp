package com.alvaronunez.data.service

import com.alvaronunez.data.Result
import com.alvaronunez.data.models.DrinkDTO
import com.alvaronunez.data.models.IngredientDTO
import com.alvaronunez.data.source.RemoteDataSource


class ServiceDataSource(private val service: Service): RemoteDataSource {

    override suspend fun getIngredients(): Result<List<IngredientDTO>> =
        try {
            Result.Response(service.apiService
                .getIngredientsAsync().await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun getDrinksByIngredient(ingredientName: String): Result<List<DrinkDTO>> =
        try {
            Result.Response(service.apiService
                .getDrinksByIngredientAsync(ingredientName).await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }


}