package com.example.midtermapplication.repositories

import com.example.midtermapplication.dao.CodingLanguageDao
import com.example.midtermapplication.dao.MusicCardDao
import com.example.midtermapplication.entities.CodingLanguage
import com.example.midtermapplication.entities.MusicCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CodingLanguageRepository @Inject constructor(
    private val dao: CodingLanguageDao
) {


    fun getAllLanguages(): Flow<List<CodingLanguage>> = dao.getAllLanguages()
    suspend fun insertLanguage(language: CodingLanguage) = dao.insertLanguage(language)
    suspend fun deleteLanguage(language: CodingLanguage) = dao.deleteLanguage(language)
    suspend fun deleteAllLanguages() = dao.deleteAllLanguages()
    suspend fun getAllLanguagesOnce(): List<CodingLanguage> = dao.getAllCodingLanguagesOnce()
}
