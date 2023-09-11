package com.example.holidate

import android.app.Application
import com.example.holidate.di.appModule
import com.example.holidate.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HolidateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HolidateApplication)
            modules(appModule, networkModule)
        }
    }
}