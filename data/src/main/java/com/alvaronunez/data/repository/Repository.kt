package com.alvaronunez.data.repository

import com.alvaronunez.data.Result
import com.alvaronunez.data.source.LocalDataSource
import com.alvaronunez.data.source.RemoteDataSource
import com.alvaronunez.domain.models.Ingredient

class Repository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {

    suspend fun getIngredients(): Result<List<Ingredient>> {
        return if (localDataSource.isIngredientsListEmpty()) {
            remoteDataSource.getIngredients().also { remoteResult ->
                if (remoteResult is Result.Response && remoteResult.data.isNotEmpty()) localDataSource.saveIngredients(remoteResult.data)
            }
        } else {
            localDataSource.getIngredients()
        }
    }

    suspend fun getDrinksByIngredient(ingredientName: String) = remoteDataSource.getDrinksByIngredient(ingredientName)

    suspend fun getIngredientsByName(ingredientName: String) = localDataSource.getIngredientsByName(ingredientName)
}