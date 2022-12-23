package com.example.cob.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cob.models.Player
import com.example.cob.models.User


@Dao
interface PlayerDao {

    @Insert
    suspend fun insert(player: Player)

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    suspend fun get(): User

    @Insert
    suspend fun insertAll(player: List<Player>)

    @Query("SELECT * FROM Player WHERE id = :id")
    suspend fun get(id: Long): Player

    @Query("SELECT * FROM Player")
    fun getAll(): LiveData<List<Player>>

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("DELETE FROM Player")
    suspend fun clear()

    @Transaction
    suspend fun replace(player: List<Player>) {
        clear()
        insertAll(player)
    }
}