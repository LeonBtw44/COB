package com.example.cob

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cob.databinding.ViewPlayersListBinding
import com.example.cob.models.Player
import com.example.cob.utils.getNameId
import com.example.cob.utils.getPlayerJob
import com.example.democlashofbattle.utils.loadImage

class PlayerViewHolder private constructor(val binding: ViewPlayersListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Player){
        loadImage(binding.avatar, item.imageUrl)
        binding.name.text = item.name
        binding.job.text = itemView.context.getString(getPlayerJob(item).getNameId())

    }

    companion object{
        fun from(parent: ViewGroup): PlayerViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewPlayersListBinding.inflate(layoutInflater, parent, false)
            return PlayerViewHolder(binding)
        }
    }
}