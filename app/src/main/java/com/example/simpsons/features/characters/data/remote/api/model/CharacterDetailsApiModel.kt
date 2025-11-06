package com.example.simpsons.features.characters.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class CharacterDetailsApiModel(
    val age: String?,
    val birthdate: String?,
    val description: String,
    val gender: String,
    val name: String,
    val occupation: String,
    val phrases: Array<String>,
    @SerializedName("portrait_path")
    val image: String,
    val status: String,
    )
