package com.example.cob.utils

import com.example.cob.models.Player

fun Map<String, Player>.toListOfPlayers() = entries.map {
    it.value.copy(remoteId = it.key)
}