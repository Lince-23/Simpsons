package com.example.simpsons.features.characters.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface CharactersApiService {
    @GET("characters?page={pageNumber}")
    fun getAllCharacters(pageNumber: String): Response<ApiResponse>
}