package com.example.cob

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cob.api.PlayerApi
import com.example.cob.database.AppDatabase
import com.example.cob.database.PlayerDao
import com.example.cob.models.Capability
import com.example.cob.models.CapabilityType
import com.example.cob.models.Player
import com.example.cob.utils.toListOfPlayers
import kotlinx.coroutines.launch

class EditViewModel : ViewModel() {
    var api: PlayerApi = PlayerApi.service
    var dao : PlayerDao = AppDatabase.INSTANCE!!.playerDao()
    val currentPlayer = MutableLiveData<Player>()

    //récupération du current user
    init {
        viewModelScope.launch {
            currentPlayer.value = dao.get(AppDatabase.INSTANCE?.playerDao()?.get()?.name!!)
        }
    }

//    fun updateCapability(index : Int, capability: Capability?) {
//        if(capability != null) {
//            when(index) {
//                1 -> capacibility1 = capability
//                2 -> capacibility2 = capability
//                3 -> capacibility3 = capability
//            }
//        }
//    }

    suspend fun updatePlayer(name: String, url : String) {
        currentPlayer.value?.let {
            player -> val player = player.copy(
            name = name,
            imageUrl = url
            )
            if(player.remoteId != null) {
                api.updatePlayer(player.remoteId, player)
                dao.update(player)
            }

        }
    }
}