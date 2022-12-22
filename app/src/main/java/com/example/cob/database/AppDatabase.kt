package com.example.cob.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cob.models.Player

@Database(entities = arrayOf(Player::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    companion object{
        var INSTANCE: AppDatabase? = null
        fun init(context: Context){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "la_base_de_donnees"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}