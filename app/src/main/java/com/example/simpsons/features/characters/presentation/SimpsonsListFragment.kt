package com.example.simpsons.features.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpsons.R
import com.example.simpsons.core.api.ApiClient
import com.example.simpsons.features.characters.data.CharactersDataRepository
import com.example.simpsons.features.characters.data.remote.api.CharactersApiRemoteDataSource
import com.example.simpsons.features.characters.domain.Character
import com.example.simpsons.features.characters.domain.GetCharactersListUseCase


/**
 * A simple [Fragment] subclass.
 * Use the [SimpsonsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpsonsListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simpsons_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            if (uiState.isLoading) {

            } else {

            }

            uiState.errorApp?.let {
                //Todo
            } ?: {
                //Todo
            }

            uiState.charactersList?.let {
                setUpRecyclerView(uiState.charactersList)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setUpRecyclerView(simpsonsList: List<Character>) {
        val adapter = SimpsonsListAdapter(simpsonsList)
        val recyclerView: RecyclerView = requireView().findViewById(R.id.fslRvCharactersList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}
