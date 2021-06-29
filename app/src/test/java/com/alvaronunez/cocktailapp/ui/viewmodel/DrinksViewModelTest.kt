package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alvaronunez.domain.models.Ingredient
import com.alvaronunez.usecases.GetIngredientsUC
import com.alvaronunez.data.Result
import com.alvaronunez.domain.models.Drink
import com.alvaronunez.usecases.GetDrinksByIngredientUC
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DrinksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getDrinksByIngredientUC: GetDrinksByIngredientUC

    @RelaxedMockK
    lateinit var observer: Observer<DrinksViewModel.DrinksModel>

    lateinit var drinksViewModel: DrinksViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        drinksViewModel = DrinksViewModel("", getDrinksByIngredientUC, Dispatchers.Unconfined)
    }

    @Test
    fun `test model content are called when data is retrieved`() {
        drinksViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Drink>>)-> Unit>()
        coEvery { getDrinksByIngredientUC.invoke("", capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Drink>())
            captureCallback.captured.invoke(fakeResult)
        }

        drinksViewModel.loadDrinks()
        coVerify { observer.onChanged(DrinksViewModel.DrinksModel.Content(listOf())) }
    }

    @Test
    fun `test model showError are called when data is retrieved with error`() {
        drinksViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Drink>>)-> Unit>()
        coEvery { getDrinksByIngredientUC.invoke("", capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        drinksViewModel.loadDrinks()
        coVerify {
            observer.onChanged(DrinksViewModel.DrinksModel.Error(null))
        }
    }

    @Test
    fun `test shows loading when data is retrieving`() {
        drinksViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Drink>>)-> Unit>()
        coEvery { getDrinksByIngredientUC.invoke("", capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Drink>())
            captureCallback.captured.invoke(fakeResult)
        }

        drinksViewModel.loadDrinks()
        coVerify { observer.onChanged(DrinksViewModel.DrinksModel.Loading(true)) }
    }

    @Test
    fun `test hides loading when data is retrieved`() {
        drinksViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Drink>>)-> Unit>()
        coEvery { getDrinksByIngredientUC.invoke("", capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Drink>())
            captureCallback.captured.invoke(fakeResult)
        }

        drinksViewModel.loadDrinks()
        coVerify { observer.onChanged(DrinksViewModel.DrinksModel.Loading(false)) }
    }

}