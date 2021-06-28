package com.alvaronunez.cocktailapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.databinding.ActivityIngredientsBinding
import com.alvaronunez.cocktailapp.ui.activity.DrinksActivity.Companion.INGREDIENT_NAME
import com.alvaronunez.cocktailapp.ui.adapter.IngredientsAdapter
import com.alvaronunez.cocktailapp.ui.common.startActivity
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel.IngredientsModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientsActivity : ScopeActivity() {

    private val viewModel: IngredientsViewModel by viewModel()
    private lateinit var binding: ActivityIngredientsBinding
    private lateinit var adapter: IngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.ingredientsToolbar.toolbar)
        adapter = IngredientsAdapter(viewModel::ingredientClicked)
        viewModel.loadIngredients()
        viewModel.model.observe(this, Observer(::updateUi))

        binding.ingredientsRecycler.adapter = adapter
    }

    private fun updateUi(model: IngredientsModel) {
        when (model) {
            is IngredientsModel.Loading -> {
                if (model.showLoading) {
                    binding.progress.visibility = View.VISIBLE
                } else {
                    binding.progress.visibility = View.GONE
                }
            }
            is IngredientsModel.Content -> adapter.ingredients = model.ingredients
            is IngredientsModel.Error -> {
                Toast.makeText(this, model.error, Toast.LENGTH_LONG).show()
            }
            is IngredientsModel.NavigateToDrinks -> startActivity<DrinksActivity> {
                putExtra(INGREDIENT_NAME, model.ingredientName)
            }
        }
    }
}