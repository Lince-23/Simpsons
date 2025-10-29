package com.example.simpsons.features.characters.domain

class GetCharactersListUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke():Result<List<Character>>{
        return charactersRepository.getAllCharactersList()
    }
}