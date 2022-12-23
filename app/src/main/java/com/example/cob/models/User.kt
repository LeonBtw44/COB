package com.example.cob.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val name: String
    )