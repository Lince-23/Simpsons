package com.example.simpsons.features.characters.domain

data class Character(
    val id: String,
    val name: String,
    val age: String?,
    val occupation: String,
    val status: String,
    val portrait_path: String?
)
