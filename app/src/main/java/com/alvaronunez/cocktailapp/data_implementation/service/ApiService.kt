package com.alvaronunez.cocktailapp.data_implementation.service


import com.alvaronunez.data.models.DrinkDTO
import com.alvaronunez.data.models.IngredientDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService{
    @GET("categories")
    fun getIngredientsAsync(): Deferred<List<IngredientDTO>>

    @GET("list/1")
    fun getDrinksByIngredientAsync(ingredientName: String): Deferred<List<DrinkDTO>>
}