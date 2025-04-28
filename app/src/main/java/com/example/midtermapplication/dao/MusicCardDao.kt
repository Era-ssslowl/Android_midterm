package com.example.midtermapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.midtermapplication.entities.MusicCard
import kotlinx.coroutines.flow.Flow

@Dao
public interface MusicCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusicCard(card: MusicCard)

    @Query("SELECT * FROM musicCards")
    fun getAllCards(): Flow<List<MusicCard>>

    @Delete
    suspend fun deleteMusicCard(card: MusicCard)

    @Query("DELETE FROM musicCards")
    suspend fun deleteAllMusicCards()

    @Query("SELECT * FROM musicCards")
    suspend fun getAllCardsOnce(): List<MusicCard>
}