package com.example.simpsons.features.characters.data.remote.api.model

data class CharactersApiModel(
    val id: String,
    val name: String,
    val occupation: String,
    val status: String,
    val portrait_path: String?
)

data class ApiResponse(
    val pages: String,
    val results: Array<CharactersApiModel>
)