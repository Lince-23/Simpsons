package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.features.characters.domain.Character

fun CharactersApiModel.toModel(): Character {
    return Character(
        this.name,
        this.age,
        this.occupation,
        this.status
    )
}

fun ApiResponse.toCharacter(): List<Character>{
    return this.results.map { character ->
        character.toModel()
    }
}