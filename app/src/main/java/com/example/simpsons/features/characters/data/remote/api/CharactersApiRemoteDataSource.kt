package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.core.ApiClient
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.ErrorApp

class CharactersApiRemoteDataSource(private val apiClient: ApiClient) {
    fun getAllSimpsons(pageNumber: Int): Result<List<Character>> {
        val apiService = apiClient.createService(CharactersApiService::class.java)
        val apiResponse = apiService.getAllCharacters(pageNumber)
        return if (apiResponse.isSuccessful && apiResponse.errorBody() == null) {
            Result.success(
                apiResponse.body()!!.toCharacter()
            )
        } else {
            Result.failure(
                ErrorApp.ServerError
            )
        }
    }
}