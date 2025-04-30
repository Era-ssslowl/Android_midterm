package com.example.midtermapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.midtermapplication.dao.CodingLanguageDao
import com.example.midtermapplication.dao.MusicCardDao
import com.example.midtermapplication.entities.CodingLanguage
import com.example.midtermapplication.entities.MusicCard

@Database(entities = [MusicCard::class, CodingLanguage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicCardDao(): MusicCardDao
    abstract fun codingLanguageDao(): CodingLanguageDao
}
