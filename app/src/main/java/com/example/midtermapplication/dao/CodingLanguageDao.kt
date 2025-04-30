package com.example.midtermapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.midtermapplication.entities.CodingLanguage
import kotlinx.coroutines.flow.Flow


@Dao
interface CodingLanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguage(language: CodingLanguage)

    @Query("SELECT * FROM CodingLanguage")
    fun getAllLanguages(): Flow<List<CodingLanguage>>

    @Delete
    suspend fun deleteLanguage(language: CodingLanguage)

    @Query("DELETE FROM CodingLanguage")
    suspend fun deleteAllLanguages()

    @Query("SELECT * FROM CodingLanguage")
    suspend fun getAllCodingLanguagesOnce(): List<CodingLanguage>
}