package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvaronunez.cocktailapp.ui.common.ScopedViewModel
import com.alvaronunez.usecases.GetIngredientsUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getIngredientsUC: GetIngredientsUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<SplashModel>()
    val model: LiveData<SplashModel>
        get() {
            if (_model.value == null) loadIngredients()
            return _model
        }

    sealed class SplashModel {
        object NavigateToIngredientsList : SplashModel()
        data class Error(val error: String?): SplashModel()
    }

    fun loadIngredients() {
        launch {
            _model.value = SplashModel.NavigateToIngredientsList

            getIngredientsUC.invoke { result ->
                when (result){
                    is com.alvaronunez.data.Result.Response -> {
                        _model.value = SplashModel.NavigateToIngredientsList
                    }
                    is com.alvaronunez.data.Result.Error -> {
                        _model.value = SplashModel.Error(result.error)
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