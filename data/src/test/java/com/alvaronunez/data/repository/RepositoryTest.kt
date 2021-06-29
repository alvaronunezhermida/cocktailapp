package com.alvaronunez.data.repository

import com.alvaronunez.data.source.LocalDataSource
import com.alvaronunez.data.source.RemoteDataSource
import com.alvaronunez.data.Result
import com.alvaronunez.data.models.DrinkMO
import com.alvaronunez.data.models.IngredientMO
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

class RepositoryTest {

    private lateinit var sut: Repository

    @RelaxedMockK
    private lateinit var mockLocalDataSource: LocalDataSource

    @RelaxedMockK
    private lateinit var mockRemoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = Repository(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `getIngredients execute isIngredientsEmpty from local`() {

        coEvery { sut.getIngredients() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isIngredientsListEmpty() } returns false

        runBlocking {
            sut.getIngredients()
            coVerify { mockLocalDataSource.isIngredientsListEmpty() }
        }
    }

    @Test
    fun `getIngredients execute getIngredients from local when there is ingredients in local`() {

        coEvery { sut.getIngredients() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isIngredientsListEmpty() } returns false

        runBlocking {
            sut.getIngredients()
            coVerify { mockLocalDataSource.getIngredients() }
        }
    }

    @Test
    fun `getIngredients execute getIngredients from remote when there isn't ingredients in local`() {
        coEvery { sut.getIngredients() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isIngredientsListEmpty() } returns true
        coEvery { mockRemoteDataSource.getIngredients() } returns Result.Response(listOf())

        runBlocking {
            sut.getIngredients()
            coVerify { mockRemoteDataSource.getIngredients() }
        }
    }

    @Test
    fun `getIngredients execute saveIngredients to local after getting ingredients from remote`() {
        val ingredients = listOf(IngredientMO.mockedIngredientFirst(), IngredientMO.mockedIngredientSecond())
        coEvery { mockLocalDataSource.isIngredientsListEmpty() } returns true
        coEvery { mockRemoteDataSource.getIngredients() } returns Result.Response(ingredients)

        runBlocking {
            sut.getIngredients()
            coVerify { mockLocalDataSource.saveIngredients(ingredients) }
        }
    }

    @Test
    fun `getDrinksByIngredient execute getDrinksByIngredient from remoteDataSource`() {
        val drinks = listOf(DrinkMO.mockedDrinkFirst(), DrinkMO.mockedDrinkSecond())
        coEvery { mockRemoteDataSource.getDrinksByIngredient("") } returns Result.Response(drinks)

        runBlocking {
            sut.getDrinksByIngredient("")
            coVerify { mockRemoteDataSource.getDrinksByIngredient("") }
        }
    }

    @Test
    fun `getIngredientsByName execute getIngredientsByName from localDataSource`() {
        val ingredients = listOf(IngredientMO.mockedIngredientFirst(), IngredientMO.mockedIngredientSecond())
        coEvery { mockLocalDataSource.getIngredientsByName("") } returns Result.Response(ingredients)

        runBlocking {
            sut.getIngredientsByName("")
            coVerify { mockLocalDataSource.getIngredientsByName("") }
        }
    }

}