package com.alvaronunez.cocktailapp.data_implementation.service

import com.alvaronunez.data.Result
import com.alvaronunez.data.source.RemoteDataSource
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.domain.models.Ingredient
import com.alvaronunez.usecases.toDrinksList
import com.alvaronunez.usecases.toIngredientsList


class ServiceDataSource(private val service: Service): RemoteDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> =
        try {
            Result.Response(
                service.apiService
                    .getIngredientsAsync().await().list.toIngredientsList()
            )
        } catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun getDrinksByIngredient(ingredientName: String): Result<List<Drink>> =
        try {
            Result.Response(service.apiService
                .getDrinksByIngredientAsync(ingredientName).await().list.toDrinksList())
        }catch (e: Exception) {
            Result.Error(e.message)
        }


}