package com.example.midtermapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CodingLanguage(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val  briefDesc: String,
    val  url: String,
    val Icon: String,
)
