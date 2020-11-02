package com.steru.suntime

import android.app.Application
import com.steru.suntime.di.sunTimeApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SunTimeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            //Use Level.NONE to fix Koin bug
            androidLogger(Level.NONE)
            androidContext(this@SunTimeApplication)
            modules(sunTimeApp)
        }
    }
}