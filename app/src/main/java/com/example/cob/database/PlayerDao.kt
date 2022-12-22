package com.example.cob.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cob.models.Player


@Dao
interface PlayerDao {

    @Insert
    suspend fun insert(player: Player)

    @Insert
    suspend fun insertAll(trips: List<Player>)

    @Query("SELECT * FROM Player WHERE id = :id")
    suspend fun get(id: Long): Player

    @Query("SELECT * FROM Player")
    fun getAll(): LiveData<List<Player>>

    @Update
    suspend fun update(trip: Player)

    @Delete
    suspend fun delete(trip: Player)

    @Query("DELETE FROM Player")
    suspend fun clear()

    @Transaction
    suspend fun replace(trips: List<Player>) {
        clear()
        insertAll(trips)
    }
}