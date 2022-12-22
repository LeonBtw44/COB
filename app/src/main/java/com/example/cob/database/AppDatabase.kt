package com.example.cob.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cob.api.PlayerApi
import com.example.cob.models.Player
import com.example.cob.utils.toListOfPlayers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
                .addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val api: PlayerApi = PlayerApi.service

                        val dao = this@Companion.INSTANCE?.playerDao()

                        GlobalScope.launch {
                           dao?.insertAll(api.getAll().toListOfPlayers())
                        }
                    }
                })
                .build()
        }
    }
}