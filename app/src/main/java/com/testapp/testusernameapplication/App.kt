package com.testapp.testusernameapplication

import android.app.Application
import com.testapp.testusernameapplication.di.data
import com.testapp.testusernameapplication.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(data, viewModule)
        }
    }
}