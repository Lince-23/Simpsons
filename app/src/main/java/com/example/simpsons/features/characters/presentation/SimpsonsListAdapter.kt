package com.example.simpsons.features.characters.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.simpsons.R
import com.example.simpsons.databinding.CharacterCardBinding
import com.example.simpsons.features.characters.domain.Character

class SimpsonsListAdapter(private val dataset: List<Character>) :
    RecyclerView.Adapter<SimpsonsListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CharacterCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character){
            binding.ccTvName.text = character.name
            binding.ccTvOccupation.text = character.occupation
            binding.ccTvStatus.text = character.status
            binding.ccIvCharacter.load(character.portrait_path)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}