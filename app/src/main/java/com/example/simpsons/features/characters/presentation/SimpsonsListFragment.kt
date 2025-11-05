package com.example.simpsons.features.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.databinding.FragmentSimpsonsListBinding
import com.example.simpsons.features.characters.data.CharactersDataRepository
import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.ErrorApp
import com.example.simpsons.features.characters.domain.GetCharactersListUseCase

class SimpsonsListFragment : Fragment() {

    private var _binding: FragmentSimpsonsListBinding? = null
    private val binding get() = _binding!!

    private val adapter = SimpsonsListAdapter(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpsonsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObserver()
        viewModel.loadCharacters()
    }

    private val page = 1
    private val viewModel = SimpsonsListViewModel(
        GetCharactersListUseCase(
            CharactersDataRepository(
                CharactersApiRemoteDataSource(
                    ApiClient()
                )
            ),
            page
        )
    )

    private fun setUpObserver() {
        val observer = Observer<SimpsonsListViewModel.UiState> { uiState ->
            val fslPbProgressBar: ProgressBar = binding.fslPbProgressBar
            val fslCvErrorView: CardView = binding.fslCvErrorView
            if (uiState.isLoading) {
                fslPbProgressBar.visibility = ProgressBar.VISIBLE
            } else {
                fslPbProgressBar.visibility = ProgressBar.GONE
            }

            if (uiState.errorApp != null) {
                val fslTvErrorText = binding.fslTvErrorText
                val fslBRetry = binding.fslBRetry
                if (uiState.errorApp == ErrorApp.ServerError) {
                    fslTvErrorText.text = "Error del servidor \nIntentelo más tarde"
                } else if (uiState.errorApp == ErrorApp.NetworkError) {
                    fslTvErrorText.text = "Error de red \nRevise su conexión a internet"
                }

                fslCvErrorView.visibility = CardView.VISIBLE
                fslBRetry.setOnClickListener {
                    viewModel.loadCharacters()
                }

            } else {
                fslCvErrorView.visibility = CardView.GONE
            }

            uiState.charactersList?.let {
                adapter.updateCharacters(newCharactersList = uiState.charactersList)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = binding.fslRvCharactersList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}
