package com.example.simpsons.features.characters.domain.usecase

import com.example.simpsons.features.characters.domain.CharactersRepository
import com.example.simpsons.features.characters.domain.model.CharacterDetails

class GetCharacterByIdUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: String):Result<CharacterDetails>{
        return charactersRepository.getCharacterById(id)
    }
}