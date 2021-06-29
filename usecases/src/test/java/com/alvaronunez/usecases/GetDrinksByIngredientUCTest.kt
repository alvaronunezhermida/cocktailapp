package com.alvaronunez.usecases

import com.alvaronunez.data.Result
import com.alvaronunez.data.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDrinksByIngredientUCTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getDrinksByIngredientUC: GetDrinksByIngredientUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getDrinksByIngredientUC = GetDrinksByIngredientUC(mockRepository)
    }

    @Test
    fun `invoke calls getIngredientsByName from repository`() {
        coEvery { mockRepository.getDrinksByIngredient("") } returns Result.Response(listOf())
        runBlocking {
            getDrinksByIngredientUC.invoke(""){}
            coVerify { mockRepository.getDrinksByIngredient("") }
        }

    }

}