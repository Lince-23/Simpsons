package com.example.simpsons.features.characters.data.remote.api

import com.example.simpsons.features.characters.data.remote.api.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {
    @GET("characters")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<ApiResponse>
}