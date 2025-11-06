package com.example.simpsons.features.characters.presentation.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.simpsons.R
import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.features.characters.data.CharactersDataRepository
import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.usecase.GetCharacterByIdUseCase
import com.example.simpsons.features.characters.presentation.list.SimpsonsListViewModel

class SimpsonsDetailsFragment : Fragment() {

    private val simpsonId: SimpsonsDetailsFragmentArgs by navArgs()
    private val viewModel = SimpsonsDetailsViewModel(
        GetCharacterByIdUseCase(
            CharactersDataRepository(
                CharactersApiRemoteDataSource(
                    ApiClient()
                )
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simpsons_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.loadCharacterDetails(simpsonId.characterId)
    }

    private fun setUpObserver() {
        val observer = Observer<SimpsonsDetailsViewModel.UiState> { uiState ->
            if (uiState.isLoading) {
                //Todo mostrar spiner
            } else {
                //Todo ocultar spiner
            }

            if (uiState.errorApp != null) {
                if (uiState.errorApp is ErrorApp.ServerError) {
                    //Todo error en caso de servidor
                } else if (uiState.errorApp is ErrorApp.NetworkError) {
                    //Todo error en caso de red
                }
            }

            uiState.character.let {
                Log.d("@dev", "${uiState.character}")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }
}