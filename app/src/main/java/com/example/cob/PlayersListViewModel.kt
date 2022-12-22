package com.example.cob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cob.api.PlayerApi
import com.example.cob.database.AppDatabase
import com.example.cob.utils.toListOfPlayers
import kotlinx.coroutines.launch

class PlayersListViewModel: ViewModel() {

    var api: PlayerApi = PlayerApi.service

    var players = AppDatabase.INSTANCE?.playerDao()?.getAll()

    fun getAll(){
        viewModelScope.launch {
            AppDatabase.INSTANCE?.playerDao()?.replace(api.getAll().toListOfPlayers())
        }
    }
}