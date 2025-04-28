package com.example.midtermapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "musicCards")
data class MusicCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val genre: String,
    val artist: String,
    val cover: String,
    val url: String
)
