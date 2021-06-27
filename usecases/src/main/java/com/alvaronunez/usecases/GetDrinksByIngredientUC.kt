package com.alvaronunez.usecases

import com.alvaronunez.data.Result
import com.alvaronunez.data.repository.Repository
import com.alvaronunez.domain.models.Drink

class GetDrinksByIngredientUC(private val repository: Repository) {
    suspend fun invoke(ingredientName: String, onResult: (Result<List<Drink>>) -> Unit) {
        when(val result: Result<List<Drink>> = repository.getDrinksByIngredient(ingredientName)) {
            is Result.Response -> {
                onResult(Result.Response(result.data))
            }
            is Result.Error -> {
                onResult(Result.Error(result.error))
            }
        }
    }
}