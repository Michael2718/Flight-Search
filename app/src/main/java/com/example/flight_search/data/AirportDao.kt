package com.example.flight_search.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE ('%' || :query || '%') OR name LIKE ('%' || :query || '%')
            ORDER BY iata_code
            LIMIT 1
        """
    )
    fun getDepartureByQuery(query: String): Flow<List<Airport>>

//    @Query(
//        """
//            SELECT * FROM airport
//            WHERE iata_code = :iataCode
//        """
//    )
//    fun getAirportByIataCodeList(iataCode: String): List<Airport>

    @Query(
        """
            SELECT * FROM airport
            ORDER BY id
        """
    )
    fun getAllAirports(): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE ('%' || :query || '%') OR name LIKE ('%' || :query || '%')
            ORDER BY iata_code
        """
    )
    fun getSuggestions(query: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code <> :departureCode
            ORDER BY passengers DESC
        """
    )
    fun getDestinations(departureCode: String): Flow<List<Airport>>
}
