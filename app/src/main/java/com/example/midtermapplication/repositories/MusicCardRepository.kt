package com.example.midtermapplication.repositories

import com.example.midtermapplication.dao.MusicCardDao
import com.example.midtermapplication.entities.MusicCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicCardRepository @Inject constructor(
    private val dao: MusicCardDao
) {
    fun getAllMusicCards(): Flow<List<MusicCard>> = dao.getAllCards()
    suspend fun getAllMusicCardsOnce(): List<MusicCard> = dao.getAllCardsOnce()
    suspend fun insertMusicCard(musicCard: MusicCard) = dao.insertMusicCard(musicCard)
    suspend fun deleteMusicCard(musicCard: MusicCard) = dao.deleteMusicCard(musicCard)
    suspend fun deleteAllMusicCards() = dao.deleteAllMusicCards()
}
