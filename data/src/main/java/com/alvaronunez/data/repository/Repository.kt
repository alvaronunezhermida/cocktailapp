package com.alvaronunez.data.repository

import com.alvaronunez.data.source.RemoteDataSource

class Repository (private val remoteDataSource: RemoteDataSource) {
    suspend fun getIngredients() = remoteDataSource.getIngredients()
    suspend fun getDrinksByIngredient(ingredientName: String) = remoteDataSource.getDrinksByIngredient(ingredientName)
}