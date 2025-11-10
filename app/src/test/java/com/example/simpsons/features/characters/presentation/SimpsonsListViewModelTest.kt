package com.example.simpsons.features.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.usecase.GetCharactersListUseCase
import com.example.simpsons.features.characters.presentation.list.SimpsonsListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SimpsonsListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var getCharactersListUseCase: GetCharactersListUseCase
    private lateinit var simpsonsListViewModel: SimpsonsListViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getCharactersListUseCase = mockk()
        simpsonsListViewModel = SimpsonsListViewModel(getCharactersListUseCase)
    }

    @Test
    fun `when UiState error is failure then _uiState error is a ServerError`() = runTest {
        //Given
        val uiState = simpsonsListViewModel.uiState
        val serverError = ErrorApp.ServerError
        //When
        coEvery { getCharactersListUseCase() } returns Result.failure(serverError)
        simpsonsListViewModel.loadCharacters()
        //Then
        coVerify(exactly = 1) { getCharactersListUseCase() }
        assertNotNull(uiState.value?.errorApp)
        assertEquals(serverError, uiState.value?.errorApp)
    }

    @Test
    fun `when UiState error is failure then _uiState error is a NetworkError`() = runTest {
        //Given
        val uiState = simpsonsListViewModel.uiState
        val networkError = ErrorApp.NetworkError
        //When
        coEvery { getCharactersListUseCase() } returns Result.failure(networkError)
        simpsonsListViewModel.loadCharacters()
        //Then
        coVerify(exactly = 1) { getCharactersListUseCase() }
        assertNotNull(uiState.value?.errorApp)
        assertEquals(networkError, uiState.value?.errorApp)
    }
}