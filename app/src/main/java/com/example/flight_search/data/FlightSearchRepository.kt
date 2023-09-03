package com.example.flight_search.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    fun getItemByIataCode(iataCode: String): Flow<List<Airport>>

    fun getItemByName(name: String): Flow<List<Airport>>

    fun getItemByQuery(query: String): Flow<List<Airport>>

    fun getAllItems(): Flow<List<Airport>>

    fun getSuggestions(query: String): Flow<List<Airport>>

    fun getDestinations(departureCode: String): Flow<List<Airport>>
}

class LocalFlightSearchRepository(private val dao: AirportDao): FlightSearchRepository {

    override fun getItemByIataCode(iataCode: String): Flow<List<Airport>> {
        return dao.getItemByIataCode(iataCode)
    }

    override fun getItemByName(name: String): Flow<List<Airport>> {
        return dao.getItemByName(name)
    }

    override fun getItemByQuery(query: String): Flow<List<Airport>> {
        return dao.getItemByQuery(query)
    }

    override fun getAllItems(): Flow<List<Airport>> {
        return dao.getAllItems()
    }

    override fun getSuggestions(query: String): Flow<List<Airport>> {
        return dao.getSuggestions(query)
    }

    override fun getDestinations(departureCode: String): Flow<List<Airport>> {
        return dao.getDestinations(departureCode)
    }
}
