package com.alvaronunez.cocktailapp.data_implementation.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alvaronunez.cocktailapp.data_implementation.database.entities.IngredientEntity

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM IngredientEntity")
    fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM IngredientEntity WHERE UPPER(name) LIKE UPPER(:ingredientName)")
    fun getIngredientsByName(ingredientName: String): List<IngredientEntity>

    @Query("SELECT COUNT(id) FROM IngredientEntity")
    fun ingredientsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIngredients(ingredients: List<IngredientEntity>)

}