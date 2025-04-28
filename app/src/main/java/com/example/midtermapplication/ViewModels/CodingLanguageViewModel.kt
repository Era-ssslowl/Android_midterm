package com.example.midtermapplication.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermapplication.entities.CodingLanguage
import com.example.midtermapplication.entities.MusicCard
import com.example.midtermapplication.repositories.CodingLanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodingLanguageViewModel @Inject constructor(
    private val repository: CodingLanguageRepository
) : ViewModel() {

    // Получаем список языков программирования через репозиторий
    val codingLanguages: StateFlow<List<CodingLanguage>> = repository.getAllLanguages()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Добавляем новый язык программирования
    fun addCodingLanguage(name: String, briefDesc: String, url: String, icon: String) {
        viewModelScope.launch {
            val newLanguage = CodingLanguage(
                name = name,
                briefDesc = briefDesc,
                url = url,
                Icon = icon
            )
            repository.insertLanguage(newLanguage)
        }
    }

    // Удалить один язык программирования
    fun deleteCodingLanguage(language: CodingLanguage) {
        viewModelScope.launch {
            repository.deleteLanguage(language)
        }
    }

    // Удалить все языки программирования
    fun deleteAllLanguages() {
        viewModelScope.launch {
            repository.deleteAllLanguages()
        }
    }


    fun insertDefaultLanguages() = viewModelScope.launch {
        val current = repository.getAllLanguagesOnce()
        if (current.isEmpty()) {
            var myLanguages = listOf(
                CodingLanguage(
                    name = "java",
                    briefDesc = "Java — строго типизированный объектно-ориентированный язык программирования общего назначения",
                    url = "https://www.java.com/ru/",
                    Icon = "https://aety.io/wp-content/uploads/2016/11/java-logo-vector.png"
                ),
                CodingLanguage(
                    name = "Spring",
                    briefDesc = "Spring Framework (или коротко Spring) — универсальный фреймворк с открытым исходным кодом для Java-платформы. ",
                    url = "https://spring.io/",
                    Icon = "https://miro.medium.com/v2/resize:fit:400/1*BfOUBD1I2fUhsXDcCB7jXQ.png"
                ),
                CodingLanguage(
                    name = "javaScript",
                    briefDesc = "JavaScript – это язык программирования, который используют разработчики для создания интерактивных веб-страниц",
                    url = "https://learn.javascript.ru/",
                    Icon = "https://i.pinimg.com/736x/19/ae/5b/19ae5b59a81e3d31806513cd5afaa385.jpg"
                ),
            )
            myLanguages.forEach { repository.insertLanguage(it) }
        }
    }
}
