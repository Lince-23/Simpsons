package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.features.characters.data.remote.api.model.ApiResponse
import com.example.simpsons.features.characters.data.remote.api.model.CharactersApiModel
import com.example.simpsons.features.characters.domain.model.Character

fun CharactersApiModel.toModel(): Character {
    return Character(
        this.id,
        this.name,
        this.occupation,
        this.status,
        "https://cdn.thesimpsonsapi.com/200${this.portrait_path}"
    )
}

fun ApiResponse.toCharacter(): List<Character> {
    return this.results.map { character ->
        character.toModel()
    }
}