package com.alvaronunez.usecases

import com.alvaronunez.data.Result
import com.alvaronunez.data.models.IngredientDTO
import com.alvaronunez.data.repository.Repository
import com.alvaronunez.domain.models.Ingredient

class GetIngredientsUC(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<Ingredient>>) -> Unit) {
        when(val result: Result<List<IngredientDTO>> = repository.getIngredients()) {
            is Result.Response -> {
                onResult(Result.Response(result.data.toIngredientsList()))
            }
            is Result.Error -> {
                onResult(Result.Error(result.error))
            }
        }
    }
}