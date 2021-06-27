package com.alvaronunez.cocktailapp.di

import android.app.Application
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.data_implementation.database.AppDatabase
import com.alvaronunez.cocktailapp.data_implementation.database.RoomDataSource
import com.alvaronunez.cocktailapp.data_implementation.service.Service
import com.alvaronunez.cocktailapp.data_implementation.service.ServiceDataSource
import com.alvaronunez.cocktailapp.ui.activity.IngredientsActivity
import com.alvaronunez.cocktailapp.ui.activity.SplashActivity
import com.alvaronunez.cocktailapp.ui.viewmodel.IngredientsViewModel
import com.alvaronunez.cocktailapp.ui.viewmodel.SplashViewModel
import com.alvaronunez.data.repository.Repository
import com.alvaronunez.data.source.LocalDataSource
import com.alvaronunez.data.source.RemoteDataSource
import com.alvaronunez.usecases.GetDrinksByIngredientUC
import com.alvaronunez.usecases.GetIngredientsUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.NONE)
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { AppDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { ServiceDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseApiUrl")) { androidApplication().getString(R.string.base_api_url) }
    factory { Service(get(named("baseApiUrl"))) }
}

private val dataModule = module {
    factory { Repository(get(), get()) }
}

private val scopesModule = module {
    scope(named<SplashActivity>()) {
        viewModel { SplashViewModel(get(), get()) }
        scoped { GetIngredientsUC(get()) }
    }

    scope(named<IngredientsActivity>()) {
        viewModel { IngredientsViewModel(get(), get()) }
        scoped { GetIngredientsUC(get()) }
    }
}