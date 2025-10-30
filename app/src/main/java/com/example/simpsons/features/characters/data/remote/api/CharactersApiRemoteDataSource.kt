package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersApiRemoteDataSource(private val apiClient: ApiClient) {
    suspend fun getAllSimpsons(pageNumber: Int): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            val apiService = apiClient.createService(CharactersApiService::class.java)
            val apiResponse = apiService.getAllCharacters(pageNumber)
            if (apiResponse.isSuccessful && apiResponse.errorBody() == null) {
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
}