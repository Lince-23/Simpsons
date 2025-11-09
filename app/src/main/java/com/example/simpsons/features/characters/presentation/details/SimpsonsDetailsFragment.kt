package com.example.simpsons.features.characters.presentation.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.databinding.FragmentSimpsonsDetailsBinding
import com.example.simpsons.features.characters.data.CharactersDataRepository
import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.usecase.GetCharacterByIdUseCase

class SimpsonsDetailsFragment : Fragment() {

    private var _binding: FragmentSimpsonsDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = SimpsonsPhrasesAdapter(emptyArray())
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
        _binding = FragmentSimpsonsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObserver()
        viewModel.loadCharacterDetails(simpsonId.characterId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = binding.fsdRvPhrases
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
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

            uiState.character?.let { characterDetails ->
                Log.d("@dev", "$characterDetails")
                binding.fsdMtMainToolbar.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                binding.fsdMtMainToolbar.title = characterDetails.name
                binding.fsdIvCharacter.load(characterDetails.image)
                binding.fsdTvDescription.text = characterDetails.description
                adapter.updatePhrases(characterDetails.phrases)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }
}