package com.example.simpsons.features.characters.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpsons.databinding.PhrasesCardBinding

class SimpsonsPhrasesAdapter(private var dataSet: Array<String>) :
    RecyclerView.Adapter<SimpsonsPhrasesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PhrasesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phrase: String) {
            binding.tvPhraseCard.text = phrase
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhrasesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phrases = dataSet[position]
        holder.bind(phrases)
    }

    override fun getItemCount() = dataSet.size

    fun updatePhrases(newPhrases: Array<String>) {
        dataSet = newPhrases
        notifyDataSetChanged()
    }
}