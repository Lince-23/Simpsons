package com.example.simpsons.features.characters.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.simpsons.databinding.CharacterCardBinding
import com.example.simpsons.features.characters.domain.model.Character

class SimpsonsListAdapter(
    private var dataset: List<Character>,
    private val listener: CharacterListener
) :
    RecyclerView.Adapter<SimpsonsListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CharacterCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
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
        val binding =
            CharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val character = dataset[position]
        holder.itemView.setOnClickListener {
            listener.onItemClick(character)
        }
        holder.bind(character)
    }

    override fun getItemCount() = dataset.size

    fun updateCharacters(newCharactersList: List<Character>) {
        dataset = newCharactersList
        notifyDataSetChanged()
    }

}

interface CharacterListener {
    fun onItemClick(character: Character)
}
