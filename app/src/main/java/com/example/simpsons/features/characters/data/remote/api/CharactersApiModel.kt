package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.features.characters.domain.Character

data class CharactersApiModel(
    val name: String,
    val age: String,
    val occupation: String,
    val status: String
)

data class ApiResponse(
    val pages: String,
    val results: Array<CharactersApiModel>
)