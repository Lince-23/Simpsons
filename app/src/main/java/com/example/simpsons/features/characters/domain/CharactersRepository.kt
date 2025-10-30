package com.example.simpsons.features.characters.domain

interface CharactersRepository {
    suspend fun getAllCharactersList(pageNumber: Int): Result<List<Character>>
}