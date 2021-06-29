package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alvaronunez.domain.models.Ingredient
import com.alvaronunez.usecases.GetIngredientsUC
import com.alvaronunez.data.Result
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getIngredientsUC: GetIngredientsUC

    @RelaxedMockK
    lateinit var observer: Observer<SplashViewModel.SplashModel>

    lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        splashViewModel = SplashViewModel(getIngredientsUC, Dispatchers.Unconfined)
    }

    @Test
    fun `test model navigateToIngredients are called when data is retrieved`() {
        splashViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Ingredient>())
            captureCallback.captured.invoke(fakeResult)
        }

        splashViewModel.loadIngredients()
        coVerify { observer.onChanged(SplashViewModel.SplashModel.NavigateToIngredientsList) }
    }

    @Test
    fun `test model showError are called when data is retrieved with error`() {
        splashViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        splashViewModel.loadIngredients()
        coVerify {
            observer.onChanged(SplashViewModel.SplashModel.Error(null))
        }
    }

}