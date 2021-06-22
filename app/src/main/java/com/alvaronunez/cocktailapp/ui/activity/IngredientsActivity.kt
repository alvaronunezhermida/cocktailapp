package com.alvaronunez.cocktailapp.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel.IngredientsModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientsActivity : AppCompatActivity() {

    private val viewModel: IngredientsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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