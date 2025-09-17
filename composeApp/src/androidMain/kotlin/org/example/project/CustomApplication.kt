package org.example.project

import android.app.Application
import qrgenerator.AppContext

class CustomApplication : Application() {
    companion object {
        lateinit var INSTANCE: CustomApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppContext.apply { set(applicationContext) }
    }
}