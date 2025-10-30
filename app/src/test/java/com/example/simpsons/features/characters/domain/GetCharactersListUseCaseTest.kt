package com.example.simpsons.features.characters.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GetCharactersListUseCaseTest {
    @Test
    fun `when invoke then get characters list`() = runTest {
        //Given
        val charactersRepositoryMock = mockk<CharactersRepository>()
        val getCharactersListUseCase = GetCharactersListUseCase(charactersRepositoryMock, 1)
        val charactersList = listOf(
            Character("Homer", "42", "Worker", "Alive", "1.webp"),
            Character("Bart", "12", "Student", "Alive", "2.webp")
        )
        coEvery { charactersRepositoryMock.getAllCharactersList(1) } returns Result.success(
            charactersList
        )
        //When
        val result = getCharactersListUseCase()
        //Then
        assert(result.isSuccess)
        assertEquals(result, Result.success(charactersList))
        coVerify(exactly = 1) { charactersRepositoryMock.getAllCharactersList(1) }
    }

    @Test
    fun `when invoke then get error`() = runTest {
        //Given
        val charactersRepositoryMock = mockk<CharactersRepository>()
        val error = ErrorApp.ServerError
        val getCharactersListUseCase = GetCharactersListUseCase(charactersRepositoryMock, 1)
        coEvery { charactersRepositoryMock.getAllCharactersList(1) } returns Result.failure(error)
        //When
        val result = getCharactersListUseCase()
        //Then
        assert(result.isFailure)
        assertEquals(result, Result.failure<ErrorApp>(error))
        coVerify(exactly = 1) { charactersRepositoryMock.getAllCharactersList(1) }
    }
}