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

class GetIngredientsUCTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getIngredientsUC: GetIngredientsUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getIngredientsUC = GetIngredientsUC(mockRepository)
    }

    @Test
    fun `invoke calls getIngredients from repository`() {
        coEvery { mockRepository.getIngredients() } returns Result.Response(listOf())
        runBlocking {
            getIngredientsUC.invoke{}
            coVerify { mockRepository.getIngredients() }
        }

    }

}