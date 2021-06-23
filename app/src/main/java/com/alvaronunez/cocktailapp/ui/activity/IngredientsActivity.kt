package com.alvaronunez.cocktailapp.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel.IngredientsModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientsActivity : ScopeActivity() {

    private val viewModel: IngredientsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_ingredients)
        viewModel.loadIngredients()
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: IngredientsModel) {
        when (model) {
            is IngredientsModel.Loading -> {}
            is IngredientsModel.Content -> {}
            is IngredientsModel.Error -> {}
        }
    }
}