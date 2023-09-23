package com.example.flight_search.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "favorite", primaryKeys = ["departure_code", "destination_code"])
data class FavoriteRoute(

    @ColumnInfo(name = "departure_code")
    val departureCode: String = "",

    @ColumnInfo(name = "destination_code")
    val destinationCode: String = ""
)

@Entity
data class FavoriteRouteExtended(
    @ColumnInfo(name = "departure_code")
    val departureCode: String = "",

    @ColumnInfo(name = "departure_name")
    val departureName: String = "",

    @ColumnInfo(name = "destination_code")
    val destinationCode: String = "",

    @ColumnInfo(name = "destination_name")
    val destinationName: String = "",
)
