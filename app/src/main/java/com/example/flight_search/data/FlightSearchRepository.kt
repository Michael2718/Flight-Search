package com.example.flight_search.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    fun getDepartureByQuery(query: String): Flow<List<Airport>>

//    fun getAirportByIataCodeList(iataCode: String): List<Airport>

    fun getAllAirports(): Flow<List<Airport>>

    fun getSuggestions(query: String): Flow<List<Airport>>

    fun getDestinations(departureCode: String): Flow<List<Airport>>

    fun getAllFavoriteRoutes(): Flow<List<FavoriteRouteExtended>>

//    suspend fun isFavoriteRoute(
//        departureCode: String,
//        destinationCode: String
//    ): Flow<List<FavoriteRoute?>>

    suspend fun addFavoriteRoute(route: FavoriteRoute)

    suspend fun removeFavoriteRoute(route: FavoriteRoute)

    suspend fun isFavoriteRoute(route: FavoriteRoute): Boolean
}

class LocalFlightSearchRepository(
    private val airportDao: AirportDao,
    private val favoriteRouteDao: FavoriteRouteDao
) : FlightSearchRepository {

    override fun getDepartureByQuery(query: String): Flow<List<Airport>> {
        return airportDao.getDepartureByQuery(query)
    }

//    override fun getAirportByIataCodeList(iataCode: String): List<Airport> {
//        return airportDao.getAirportByIataCodeList(iataCode)
//    }

    override fun getAllAirports(): Flow<List<Airport>> {
        return airportDao.getAllAirports()
    }

    override fun getSuggestions(query: String): Flow<List<Airport>> {
        return airportDao.getSuggestions(query)
    }

    override fun getDestinations(departureCode: String): Flow<List<Airport>> {
        return airportDao.getDestinations(departureCode)
    }

    override fun getAllFavoriteRoutes(): Flow<List<FavoriteRouteExtended>> {
        return favoriteRouteDao.getAllFavoriteRoutes()
    }

//    override suspend fun isFavoriteRoute(
//        departureCode: String,
//        destinationCode: String
//    ): Flow<List<FavoriteRoute?>> {
//        return favoriteRouteDao.isFavoriteRoute(departureCode, destinationCode)
//    }

    override suspend fun addFavoriteRoute(route: FavoriteRoute) {
        favoriteRouteDao.addFavoriteRoute(route)
    }

    override suspend fun removeFavoriteRoute(route: FavoriteRoute) {
        favoriteRouteDao.deleteFavoriteRoute(route)
    }

    override suspend fun isFavoriteRoute(route: FavoriteRoute): Boolean {
        return favoriteRouteDao.isFavoriteRoute(
            route.departureCode,
            route.destinationCode
        ) != null
    }
}
