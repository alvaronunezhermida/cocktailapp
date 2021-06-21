package com.alvaronunez.data.repository

import com.alvaronunez.data.Result
import com.alvaronunez.data.models.IngredientDTO
import com.alvaronunez.data.source.LocalDataSource
import com.alvaronunez.data.source.RemoteDataSource

class Repository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {

    suspend fun getIngredients(): Result<List<IngredientDTO>> {
        return if (localDataSource.isIngredientsListEmpty()) {
            remoteDataSource.getIngredients().also { remoteResult ->
                if (remoteResult is Result.Response && remoteResult.data.isNotEmpty()) localDataSource.saveIngredients(remoteResult.data)
            }
        } else {
            localDataSource.getIngredients()
        }
    }

    suspend fun getDrinksByIngredient(ingredientName: String) = remoteDataSource.getDrinksByIngredient(ingredientName)
}