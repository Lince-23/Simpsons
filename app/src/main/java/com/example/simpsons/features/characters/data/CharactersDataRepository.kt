package com.example.simpsons.features.characters.data

import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.model.Character
import com.example.simpsons.features.characters.domain.CharactersRepository
import com.example.simpsons.features.characters.domain.model.CharacterDetails

class CharactersDataRepository(private val charactersApiRemoteDataSource: CharactersApiRemoteDataSource): CharactersRepository {
    override suspend fun getAllCharactersList(pageNumber: Int): Result<List<Character>> {
        return charactersApiRemoteDataSource.getAllSimpsons(pageNumber)
    }

    override suspend fun getCharacterById(id: String): Result<CharacterDetails> {
        return charactersApiRemoteDataSource.getCharacterById(id)
    }
}
