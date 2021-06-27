package com.alvaronunez.cocktailapp

import android.app.Application
import com.alvaronunez.cocktailapp.di.initDI

class CocktailApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}