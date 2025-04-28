package com.example.midtermapplication.db
import android.content.Context
import androidx.room.Room
import com.example.midtermapplication.dao.CodingLanguageDao
import com.example.midtermapplication.dao.MusicCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideMusicCardDao(database: AppDatabase): MusicCardDao = database.musicCardDao()

    @Singleton
    @Provides
    fun provideCodingLanguageDao(database: AppDatabase): CodingLanguageDao = database.codingLanguageDao()

}
