package com.example.simpsons.features.characters.domain

import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class GetCharactersListUseCaseTest {
    @Test
    fun `when invoke then get characters list`(){
        //Given
        val charactersRepositoryMock = mockk<CharactersRepository>()
        val getCharactersListUseCase = GetCharactersListUseCase(charactersRepositoryMock, 1)
        val
        //When
        getCharactersListUseCase()
        //Then
    }
}