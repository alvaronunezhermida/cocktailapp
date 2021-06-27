package com.alvaronunez.cocktailapp.data_implementation.service


import com.alvaronunez.cocktailapp.data_implementation.models.DrinkDTO
import com.alvaronunez.cocktailapp.data_implementation.models.IngredientDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService{
    @GET("list.php?i=list")
    fun getIngredientsAsync(): Deferred<List<IngredientDTO>>

    @GET("filter.php?i=Gin")
    fun getDrinksByIngredientAsync(ingredientName: String): Deferred<List<DrinkDTO>>
}