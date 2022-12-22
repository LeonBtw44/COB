package com.example.cob

import android.app.Application
import com.example.cob.database.AppDatabase

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase.init(this)
    }
}