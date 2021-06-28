package com.alvaronunez.cocktailapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.databinding.ActivitySplashBinding
import com.alvaronunez.cocktailapp.ui.common.startActivity
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel.SplashModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : ScopeActivity() {

    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadIngredients()
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: SplashModel) {
        when (model) {
            is SplashModel.NavigateToIngredientsList -> {
                startActivity<IngredientsActivity> {}
                finish()
            }
            is SplashModel.Error -> {
                Toast.makeText(this, model.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}