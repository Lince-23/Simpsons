package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.features.characters.domain.model.Character
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.model.CharacterDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class CharactersApiRemoteDataSource(private val apiClient: ApiClient) {

    private val apiService = apiClient.createService(CharactersApiService::class.java)

    suspend fun getAllSimpsons(pageNumber: Int): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
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

            } catch (e: UnknownHostException) {
                Result.failure(
                    ErrorApp.NetworkError
                )
            }
        }
    }

    suspend fun getCharacterById(id: String): Result<CharacterDetails> {
        return withContext(Dispatchers.IO) {
            try {
                val apiResponse = apiService.getCharacterById(id)
                if (apiResponse.isSuccessful && apiResponse.errorBody() == null) {
                    Result.success(apiResponse.body()!!.toModel())
                } else {
                    Result.failure(ErrorApp.ServerError)
                }
            } catch (e: UnknownHostException) {
                Result.failure(ErrorApp.NetworkError)
            }
        }
    }
}