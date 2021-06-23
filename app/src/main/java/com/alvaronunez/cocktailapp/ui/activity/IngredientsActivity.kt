package com.alvaronunez.cocktailapp.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.adapter.IngredientsAdapter
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel.IngredientsModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientsActivity : ScopeActivity() {

    private val viewModel: IngredientsViewModel by viewModel()

    private val adapter: IngredientsAdapter = IngredientsAdapter()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_ingredients)
        viewModel.loadIngredients()
        viewModel.model.observe(this, Observer(::updateUi))

        findViewById<RecyclerView>(R.id.ingredientsRecycler).adapter = adapter
    }

    private fun updateUi(model: IngredientsModel) {
        when (model) {
            is IngredientsModel.Loading -> {}
            is IngredientsModel.Content -> adapter.ingredients = model.ingredients
            is IngredientsModel.Error -> {}
        }
    }
}