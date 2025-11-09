package com.example.simpsons.features.characters.domain

import com.example.simpsons.features.characters.domain.model.Character
import com.example.simpsons.features.characters.domain.model.CharacterDetails

interface CharactersRepository {
    suspend fun getAllCharactersList(pageNumber: Int): Result<List<Character>>
    suspend fun getCharacterById(id: String): Result<CharacterDetails>
}