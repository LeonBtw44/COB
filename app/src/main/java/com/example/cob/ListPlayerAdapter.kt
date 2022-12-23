package com.example.cob

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cob.models.Player

class ListPlayerAdapter(val clickListener: (Player) -> Unit): ListAdapter<Player, PlayerViewHolder>(PlayerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

}

/*class ListTripAdapter(val clickListener: (Long) -> Unit): ListAdapter<Trip, TripViewHolder>(TripDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}*/