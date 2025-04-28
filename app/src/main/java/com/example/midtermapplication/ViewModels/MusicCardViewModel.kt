package com.example.midtermapplication.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermapplication.entities.MusicCard
import com.example.midtermapplication.repositories.MusicCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicCardViewModel @Inject constructor(
    private val repository: MusicCardRepository
) : ViewModel() {

    // Список всех карточек
    val musicCards: StateFlow<List<MusicCard>> = repository.getAllMusicCards()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Добавить карточку
    fun addMusicCard(name: String, genre: String, artist: String, cover: String, url: String) {
        viewModelScope.launch {
            val newCard = MusicCard(
                name = name,
                genre = genre,
                artist = artist,
                cover = cover,
                url = url
            )
            repository.insertMusicCard(newCard)
        }
    }

    // Удалить одну карточку
    fun deleteMusicCard(card: MusicCard) {
        viewModelScope.launch {
            repository.deleteMusicCard(card)
        }
    }

    // Удалить все карточки
    fun deleteAllCards() {
        viewModelScope.launch {
            repository.deleteAllMusicCards()
        }
    }

    // Вставить дефолтные альбомы, если база пустая
    fun insertDefaultAlbums() = viewModelScope.launch {
        val current = repository.getAllMusicCardsOnce()
        if (current.isEmpty()) {
            val defaultAlbums = listOf(
                MusicCard(
                    name = "Vessel",
                    genre = "Alternative-rock",
                    artist = "Twenty-one pilots",
                    cover = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/73/a7/23/73a7230c-19df-02a4-ff4e-53944024f63d/075679957924.jpg/600x600bb.jpg",
                    url = "https://open.spotify.com/album/2r2r78NE05YjyHyVbVgqFn"
                ),
                MusicCard(
                    name = "Ok computer",
                    genre = "Rock",
                    artist = "Radiohead",
                    cover = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/07/60/ba/0760ba0f-148c-b18f-d0ff-169ee96f3af5/634904078164.png/600x600bb.jpg",
                    url = "https://open.spotify.com/album/6dVIqQ8qmQ5GBnJ9shOYGE"
                ),
                MusicCard(
                    name = "Часть чего-то большего",
                    genre = "Rock",
                    artist = "Валентин Стрыкало",
                    cover = "https://is1-ssl.mzstatic.com/image/thumb/Music62/v4/18/08/7c/18087c3e-2899-70ba-f3fc-4cfba1924985/191061114437.jpg/600x600bb.jpg",
                    url = "https://open.spotify.com/album/0x4WtpS8RL49nXoHlMgQsW"
                )
            )
            defaultAlbums.forEach { repository.insertMusicCard(it) }
        }
    }
}
