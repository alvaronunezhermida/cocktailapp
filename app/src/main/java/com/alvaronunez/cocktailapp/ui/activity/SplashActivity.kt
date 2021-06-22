package com.alvaronunez.cocktailapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel.SplashModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)
        Toast.makeText(this, "HOLA", Toast.LENGTH_LONG).show()
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