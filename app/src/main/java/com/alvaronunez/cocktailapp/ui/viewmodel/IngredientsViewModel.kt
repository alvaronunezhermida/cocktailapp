package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvaronunez.cocktailapp.ui.common.ScopedViewModel
import com.alvaronunez.data.Result
import com.alvaronunez.domain.models.Ingredient
import com.alvaronunez.usecases.GetIngredientsByNameUC
import com.alvaronunez.usecases.GetIngredientsUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class IngredientsViewModel(
    private val getIngredientsUC: GetIngredientsUC,
    private val getIngredientsByNameUC: GetIngredientsByNameUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<IngredientsModel>()
    val model: LiveData<IngredientsModel> = _model

    sealed class IngredientsModel {
        data class Loading(val showLoading: Boolean) : IngredientsModel()
        data class Content(val ingredients: List<Ingredient>) : IngredientsModel()
        data class Error(val error: String?) : IngredientsModel()
        data class NavigateToDrinks(val ingredientName: String) : IngredientsModel()
    }

    fun loadIngredients() {
        launch {
            _model.value = IngredientsModel.Loading(true)
            getIngredientsUC.invoke { result ->
                when (result) {
                    is Result.Response -> {
                        _model.value = IngredientsModel.Content(result.data)
                        _model.value = IngredientsModel.Loading(false)
                    }
                    is Result.Error -> {
                        _model.value = IngredientsModel.Error(result.error)
                    }
                }
            }
        }
    }

    fun ingredientClicked(ingredientName: String) {
        _model.value = IngredientsModel.NavigateToDrinks(ingredientName)
    }

    fun searchIngredient(ingredientName: String) {
        launch {
            getIngredientsByNameUC.invoke(ingredientName) { result ->
                when (result) {
                    is Result.Response -> {
                        _model.value = IngredientsModel.Content(result.data)
                    }
                    is Result.Error -> {
                        _model.value = IngredientsModel.Error(result.error)
                    }
                }
            }
        }
    }

    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}