package com.example.compose_oech

import android.app.Application
import android.util.Log
import com.example.compose_oech.di.AppComponent
import com.example.compose_oech.di.DaggerAppComponent

class ChatApp : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        Log.e("maksim", "Application created")

        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}
