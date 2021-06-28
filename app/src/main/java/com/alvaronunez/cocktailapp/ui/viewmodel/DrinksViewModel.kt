package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvaronunez.cocktailapp.ui.common.ScopedViewModel
import com.alvaronunez.data.Result
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.usecases.GetDrinksByIngredientUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DrinksViewModel(
    private val ingredientName: String,
    private val getDrinksByIngredientUC: GetDrinksByIngredientUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<DrinksModel>()
    val model: LiveData<DrinksModel>
        get() {
            if (_model.value == null) loadDrinks()
            return _model
        }

    sealed class DrinksModel {
        data class Loading(val showLoading: Boolean) : DrinksModel()
        data class Content(val drinks: List<Drink>) : DrinksModel()
        data class Error(val error: String?) : DrinksModel()
    }

    fun loadDrinks() {
        launch {
            _model.value = DrinksModel.Loading(true)
            getDrinksByIngredientUC.invoke(ingredientName) { result ->
                when (result) {
                    is Result.Response -> {
                        _model.value = DrinksModel.Content(result.data)
                        _model.value = DrinksModel.Loading(false)
                    }
                    is Result.Error -> {
                        _model.value = DrinksModel.Error(result.error)
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