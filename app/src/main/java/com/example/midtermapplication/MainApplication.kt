package com.example.midtermapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Инициализация Hilt будет происходить здесь
    }
}