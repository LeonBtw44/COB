package com.example.cob

import androidx.recyclerview.widget.DiffUtil
import com.example.cob.models.Player

class PlayerDiffCallback: DiffUtil.ItemCallback<Player>()  {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}