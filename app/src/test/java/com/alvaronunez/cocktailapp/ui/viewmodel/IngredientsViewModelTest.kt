package com.alvaronunez.cocktailapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alvaronunez.domain.models.Ingredient
import com.alvaronunez.usecases.GetIngredientsUC
import com.alvaronunez.data.Result
import com.alvaronunez.usecases.GetIngredientsByNameUC
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class IngredientsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getIngredientsUC: GetIngredientsUC

    @RelaxedMockK
    lateinit var getIngredientsByNameUC: GetIngredientsByNameUC

    @RelaxedMockK
    lateinit var observer: Observer<IngredientsViewModel.IngredientsModel>

    lateinit var ingredientsViewModel: IngredientsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ingredientsViewModel = IngredientsViewModel(getIngredientsUC, getIngredientsByNameUC, Dispatchers.Unconfined)
    }

    @Test
    fun `test content are called when data is retrieved`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Ingredient>())
            captureCallback.captured.invoke(fakeResult)
        }

        ingredientsViewModel.loadIngredients()
        coVerify { observer.onChanged(IngredientsViewModel.IngredientsModel.Content(listOf())) }
    }

    @Test
    fun `test content are called when data from search is retrieved`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsByNameUC.invoke("", capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Ingredient>())
            captureCallback.captured.invoke(fakeResult)
        }

        ingredientsViewModel.searchIngredient("")
        coVerify { observer.onChanged(IngredientsViewModel.IngredientsModel.Content(listOf())) }
    }

    @Test
    fun `test shows loading when data is retrieving`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Ingredient>())
            captureCallback.captured.invoke(fakeResult)
        }

        ingredientsViewModel.loadIngredients()
        coVerify { observer.onChanged(IngredientsViewModel.IngredientsModel.Loading(true)) }
    }

    @Test
    fun `test hides loading when data is retrieved`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<Ingredient>())
            captureCallback.captured.invoke(fakeResult)
        }

        ingredientsViewModel.loadIngredients()
        coVerify { observer.onChanged(IngredientsViewModel.IngredientsModel.Loading(false)) }
    }

    @Test
    fun `test showError are called when data is retrieved with error`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsUC.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        ingredientsViewModel.loadIngredients()
        coVerify {
            observer.onChanged(IngredientsViewModel.IngredientsModel.Error(null))
        }
    }

    @Test
    fun `test showError are called when data from search is retrieved with error`() {
        ingredientsViewModel.model.observeForever(observer)

        val captureCallback = slot<(Result<List<Ingredient>>)-> Unit>()
        coEvery { getIngredientsByNameUC.invoke("", capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        ingredientsViewModel.searchIngredient("")
        coVerify {
            observer.onChanged(IngredientsViewModel.IngredientsModel.Error(null))
        }
    }

    @Test
    fun `test model NavigateToDrinks are called when ingredient is clicked`() {
        ingredientsViewModel.model.observeForever(observer)

        ingredientsViewModel.ingredientClicked("")
        coVerify {
            observer.onChanged(IngredientsViewModel.IngredientsModel.NavigateToDrinks(""))
        }
    }

}