package com.example.simpsons.features.characters.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simpsons.R


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simpsons_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SimpsonsListFragment().apply {
            }
    }
}