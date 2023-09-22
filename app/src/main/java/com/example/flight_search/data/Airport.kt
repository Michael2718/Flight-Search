package com.example.flight_search.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "iata_code")
    val iataCode: String = "",

    val name: String = "",

    val passengers: Int = 0
)

data class FlightPair(
    val departure: Airport,
    val destination: Airport
)
