package com.example.flight_search

import android.app.Application
import com.example.flight_search.data.AppContainer
import com.example.flight_search.data.AppDataContainer

class FlightSearchApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}