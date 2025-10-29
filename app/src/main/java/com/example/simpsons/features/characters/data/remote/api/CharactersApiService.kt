package com.example.simpsons.features.characters.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {
    @GET("characters")
    fun getAllCharacters(@Query("page") page: Int): Response<ApiResponse>
}