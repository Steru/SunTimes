package com.steru.suntime

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SunTimeApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@SunTimeApplication)
            //TODO: declare modules
        }
    }

}