package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.features.characters.data.remote.api.model.CharacterDetailsApiModel
import com.example.simpsons.features.characters.domain.model.CharacterDetails

fun CharacterDetailsApiModel.toModel(): CharacterDetails {
    return CharacterDetails(
        this.age,
        this.birthdate,
        this.description,
        this.gender,
        this.name,
        this.occupation,
        this.phrases,
        this.image,
        this.status
    )
}