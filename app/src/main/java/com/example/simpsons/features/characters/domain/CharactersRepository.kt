package com.example.simpsons.features.characters.domain

interface CharactersRepository {
    fun getAllCharactersList(): Result<List<Character>>
}