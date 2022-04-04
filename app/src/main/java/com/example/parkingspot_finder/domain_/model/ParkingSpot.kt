package com.example.parkingspot_finder.domain_.model

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id:Int? = null
)
//mapper is used to convert parking spot entity to parking spot class that belongs to data layer