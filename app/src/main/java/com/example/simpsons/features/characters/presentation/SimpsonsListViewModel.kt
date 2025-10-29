package com.example.simpsons.features.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.GetCharactersListUseCase

class SimpsonsListViewModel(private val getCharactersListUseCase: GetCharactersListUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadCharacters() {
        getCharactersListUseCase().fold(
            {
                isSuccess(it)
            },
            {
                isFailure(it as ErrorApp)
            }
        )
    }

    private fun isSuccess(characters: List<Character>) {
        _uiState.value = UiState(charactersList = characters)
    }

    private fun isFailure(error: ErrorApp) {
        _uiState.value = UiState(errorApp = error)
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val charactersList: List<Character>? = null
    )
}