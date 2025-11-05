package com.example.simpsons.features.characters.domain

import com.example.simpsons.features.characters.domain.model.Character

interface CharactersRepository {
    suspend fun getAllCharactersList(pageNumber: Int): Result<List<Character>>
}