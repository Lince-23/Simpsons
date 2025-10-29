package com.example.simpsons.features.characters.domain

interface CharactersRepository {
    fun getAllCharactersList(pageNumber: String): Result<List<Character>>
}