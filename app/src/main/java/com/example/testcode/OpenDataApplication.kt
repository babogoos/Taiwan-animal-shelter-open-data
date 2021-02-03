package com.example.testcode

import android.app.Application
import com.example.testcode.di.appModule
import com.example.testcode.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by dion on 2021/02/01.
 */
class OpenDataApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OpenDataApplication)
            val list = listOf(appModule, dataModule)
            modules(list)
        }
    }
}