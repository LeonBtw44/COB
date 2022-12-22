package com.example.cob

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cob.databinding.ViewPlayersListBinding
import com.example.cob.models.Player

class PlayerViewHolder private constructor(val binding: ViewPlayersListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Player){

    }

    companion object{
        fun from(parent: ViewGroup): PlayerViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewPlayersListBinding.inflate(layoutInflater, parent, false)
            return PlayerViewHolder(binding)
        }
    }
}