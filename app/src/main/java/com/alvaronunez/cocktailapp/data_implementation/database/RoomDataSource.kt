package com.alvaronunez.cocktailapp.data_implementation.database

import com.alvaronunez.data.Result
import com.alvaronunez.data.source.LocalDataSource
import com.alvaronunez.domain.models.Ingredient

class RoomDataSource(db: AppDatabase) : LocalDataSource {

    private val ingredientDao = db.ingredientsDao()

    override suspend fun isIngredientsListEmpty(): Boolean =
        try {
            ingredientDao.ingredientsCount() <= 0
        }catch (e: Exception) {
            true
        }

    override suspend fun getIngredients(): Result<List<Ingredient>> =
        try {
            Result.Response(ingredientDao.getAll().toDomainIngredientList())
        }catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun saveIngredients(ingredients: List<Ingredient>): Result<Boolean> =
        try {
            ingredientDao.insertIngredients(ingredients.toIngredientEntityList())
            Result.Response(true)
        }catch (e: Exception) {
            Result.Error(e.message)
        }

}