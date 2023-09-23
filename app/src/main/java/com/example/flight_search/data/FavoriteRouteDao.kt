package com.example.flight_search.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteRouteDao {
//    @Query(
//        """
//            SELECT *
//            FROM favorite;
//        """
//    )
//    fun getAllFavoriteRoutes(): Flow<List<FavoriteRoute>>

    @Query(
        """
            SELECT favorite.departure_code,
                   airport_1.name AS departure_name,
                   favorite.destination_code,
                   airport_2.name AS destination_name
            FROM favorite
                JOIN airport AS airport_1 ON favorite.departure_code = airport_1.iata_code
                JOIN airport AS airport_2 ON favorite.destination_code = airport_2.iata_code
            ORDER BY departure_code;
        """
    )
    fun getAllFavoriteRoutes(): Flow<List<FavoriteRouteExtended>>

    @Query(
        """
            SELECT *
            FROM favorite
            WHERE departure_code = :departureCode AND destination_code = :destinationCode
            LIMIT 1;
        """
    )
    suspend fun isFavoriteRoute(departureCode: String, destinationCode: String) : FavoriteRoute?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteRoute(favoriteRoute: FavoriteRoute)

    @Delete
    suspend fun deleteFavoriteRoute(favoriteRoute: FavoriteRoute)
}
