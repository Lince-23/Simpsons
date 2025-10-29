package com.example.simpsons.features.characters.domain

sealed class ErrorApp : Throwable() {
    object ServerError : ErrorApp()
}