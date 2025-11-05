package com.example.simpsons.features.characters.domain.usecase

import com.example.simpsons.features.characters.domain.CharactersRepository
import com.example.simpsons.features.characters.domain.model.Character

class GetCharactersListUseCase(private val charactersRepository: CharactersRepository, private val pageNumber: Int) {
    suspend operator fun invoke():Result<List<Character>>{
        return charactersRepository.getAllCharactersList(pageNumber)
    }
}