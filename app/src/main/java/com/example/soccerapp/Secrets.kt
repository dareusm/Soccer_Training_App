package com.example.soccerapp

import android.app.Application
import com.google.android.libraries.places.api.Places

class Secrets: Application() {
    override fun onCreate(){
        super.onCreate()
        Places.initialize(this, BuildConfig.MAPS_API_KEY)
    }
}