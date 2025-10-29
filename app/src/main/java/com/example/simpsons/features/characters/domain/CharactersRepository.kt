package com.example.simpsons.features.characters.domain

interface CharactersRepository {
    fun getAllCharactersList(pageNumber: Int): Result<List<Character>>
}