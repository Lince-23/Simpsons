package com.example.simpsons.features.characters.domain.model

data class CharacterDetails(
    val age:String?,
    val birthdate:String?,
    val description: String,
    val gender: String,
    val name:String,
    val occupation:String,
    val phrases: Array<String>,
    val image: String,
    val status: String,
)
