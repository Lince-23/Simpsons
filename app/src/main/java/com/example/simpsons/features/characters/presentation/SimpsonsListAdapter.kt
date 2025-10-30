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
import com.example.simpsons.features.characters.domain.Character

class SimpsonsListAdapter(private val dataset: List<Character>) :
    RecyclerView.Adapter<SimpsonsListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ccTvName: TextView
        val ccTvOccupation: TextView
        val ccTvStatus: TextView
        val ccIvCharacter: ImageView

        init {
            ccTvName = view.findViewById(R.id.ccTvName)
            ccTvOccupation = view.findViewById(R.id.ccTvOccupation)
            ccTvStatus = view.findViewById(R.id.ccTvStatus)
            ccIvCharacter = view.findViewById(R.id.ccIvCharacter)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.ccTvName.text = dataset[position].name
        holder.ccTvOccupation.text = dataset[position].occupation
        holder.ccTvStatus.text = dataset[position].status
        Log.d("@dev", "${dataset[position].portrait_path}")
        holder.ccIvCharacter.load(dataset[position].portrait_path)
    }

    override fun getItemCount() = dataset.size
}