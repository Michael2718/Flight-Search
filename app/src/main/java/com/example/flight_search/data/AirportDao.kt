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
            ORDER BY id ASC
        """
    )
    fun getAllItems(): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE ('%' || :query || '%') OR name LIKE ('%' || :query || '%')
            LIMIT 10
        """
    )
    fun getSuggestions(query: String): Flow<List<Airport>>
}
