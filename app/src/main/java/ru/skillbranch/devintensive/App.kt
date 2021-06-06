package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context

// единная точка входа для приложения
class App: Application() {
    companion object {
        private var instance: App? = null

        fun applicationContext(): Context = instance!!.applicationContext
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }
}