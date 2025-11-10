package com.example.simpsons.features.characters.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.model.CharacterDetails
import com.example.simpsons.features.characters.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class SimpsonsDetailsViewModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadCharacterDetails(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)

            getCharacterByIdUseCase(id).fold(
                {
                    isSuccess(it)
                },
                {
                    isFailure(it as ErrorApp)
                }
            )
        }
    }

    private fun isSuccess(detailedCharacter: CharacterDetails) {
        _uiState.value = UiState(character = detailedCharacter)
    }

    private fun isFailure(error: ErrorApp) {
        _uiState.value = UiState(errorApp = error)
    }


    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val character: CharacterDetails? = null
    )
}