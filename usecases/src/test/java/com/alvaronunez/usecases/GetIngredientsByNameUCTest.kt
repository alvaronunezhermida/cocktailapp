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

class GetIngredientsByNameUCTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getIngredientsByNameUC: GetIngredientsByNameUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getIngredientsByNameUC = GetIngredientsByNameUC(mockRepository)
    }

    @Test
    fun `invoke calls getIngredientsByName from repository`() {
        coEvery { mockRepository.getIngredientsByName("") } returns Result.Response(listOf())
        runBlocking {
            getIngredientsByNameUC.invoke(""){}
            coVerify { mockRepository.getIngredientsByName("") }
        }

    }

}