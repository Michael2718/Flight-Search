package com.example.flight_search.data

import android.content.Context

interface AppContainer {
    val flightSearchRepository: FlightSearchRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val flightSearchRepository: FlightSearchRepository by lazy {
        LocalFlightSearchRepository(
            airportDao = FlightSearchDatabase.getDatabase(context).airportDao(),
            favoriteRouteDao = FlightSearchDatabase.getDatabase(context).favoriteRouteDao()
        )
    }
}