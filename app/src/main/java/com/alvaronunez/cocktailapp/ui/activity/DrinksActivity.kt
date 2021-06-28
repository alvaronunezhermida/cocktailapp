package com.alvaronunez.cocktailapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.databinding.ActivityDrinksBinding
import com.alvaronunez.cocktailapp.databinding.ActivityIngredientsBinding
import com.alvaronunez.cocktailapp.ui.adapter.DrinksAdapter
import com.alvaronunez.cocktailapp.ui.adapter.IngredientsAdapter
import com.alvaronunez.cocktailapp.ui.viewmodel.DrinksViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.DrinksViewModel.DrinksModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinksActivity : ScopeActivity() {

    companion object {
        const val INGREDIENT_NAME = "DrinksActivity:ingredientName"
    }

    private val viewModel: DrinksViewModel by viewModel {
        parametersOf(intent.getStringExtra(INGREDIENT_NAME))
    }
    private lateinit var binding: ActivityDrinksBinding
    private val adapter: DrinksAdapter = DrinksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer(::updateUi))

        binding.drinksRecycler.adapter = adapter
    }

    private fun updateUi(model: DrinksModel) {
        when (model) {
            is DrinksModel.Loading -> {
                if (model.showLoading) {
                    binding.progress.visibility = View.VISIBLE
                } else {
                    binding.progress.visibility = View.GONE
                }
            }
            is DrinksModel.Content -> adapter.drinks = model.drinks
            is DrinksModel.Error -> {
                Toast.makeText(this, model.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}