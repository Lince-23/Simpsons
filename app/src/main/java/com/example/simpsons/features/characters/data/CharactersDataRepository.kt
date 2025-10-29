package com.example.simpsons.features.characters.data

import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.CharactersRepository

class CharactersDataRepository(private val charactersApiRemoteDataSource: CharactersApiRemoteDataSource): CharactersRepository {
    override fun getAllCharactersList(pageNumber: Int): Result<List<Character>> {
        return charactersApiRemoteDataSource.getAllSimpsons(pageNumber)
    }
}
