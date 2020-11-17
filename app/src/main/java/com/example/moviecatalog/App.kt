@file:Suppress("unused")

package com.example.moviecatalog

import android.app.Application
import com.example.moviecatalog.di.appModule
import com.example.moviecatalog.di.repoModule
import com.example.moviecatalog.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}