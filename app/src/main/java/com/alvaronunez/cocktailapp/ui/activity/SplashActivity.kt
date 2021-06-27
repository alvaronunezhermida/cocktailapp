package com.alvaronunez.cocktailapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel.SplashModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : ScopeActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.loadIngredients()
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: SplashModel) {
        when (model) {
            is SplashModel.NavigateToIngredientsList -> startActivity(Intent(this, IngredientsActivity::class.java))
            is SplashModel.Error -> { Toast.makeText(this, model.error, Toast.LENGTH_LONG).show() }
        }
    }
}