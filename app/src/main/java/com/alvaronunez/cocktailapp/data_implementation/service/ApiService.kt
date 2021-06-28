package com.alvaronunez.cocktailapp.data_implementation.service


import com.alvaronunez.cocktailapp.data_implementation.models.DrinkDTO
import com.alvaronunez.cocktailapp.data_implementation.models.DrinkWrapperDTO
import com.alvaronunez.cocktailapp.data_implementation.models.IngredientWrapperDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("list.php?i=list")
    fun getIngredientsAsync(): Deferred<IngredientWrapperDTO>

    @GET("filter.php")
    fun getDrinksByIngredientAsync(@Query("i") ingredientName: String): Deferred<DrinkWrapperDTO>
}