package com.example.flight_search.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteRoute(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

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
