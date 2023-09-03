package com.example.flight_search.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code = :iataCode
        """
    )
    fun getItemByIataCode(iataCode: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE name = :name
        """
    )
    fun getItemByName(name: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE ('%' || :query || '%') OR name LIKE ('%' || :query || '%')
            ORDER BY iata_code ASC
            LIMIT 1
        """
    )
    fun getItemByQuery(query: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            ORDER BY id ASC
        """
    )
    fun getAllItems(): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE ('%' || :query || '%') OR name LIKE ('%' || :query || '%')
            ORDER BY iata_code ASC
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
