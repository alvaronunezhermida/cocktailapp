package com.alvaronunez.usecases

import com.alvaronunez.data.Result
import com.alvaronunez.data.repository.Repository
import com.alvaronunez.domain.models.Ingredient

class GetIngredientsUC(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<Ingredient>>) -> Unit) {
        when(val result: Result<List<Ingredient>> = repository.getIngredients()) {
            is Result.Response -> {
                onResult(Result.Response(result.data))
            }
            is Result.Error -> {
                onResult(Result.Error(result.error))
            }
        }
    }
}