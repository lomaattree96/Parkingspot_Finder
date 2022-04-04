package com.example.parkingspot_finder.domain_.repository

import com.example.parkingspot_finder.domain_.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingspotRepository {

    suspend fun insertParkingSpot(spot: ParkingSpot)

    suspend fun deleteParkingSpot(spot: ParkingSpot)

    fun getParkingSpot() : Flow<List<ParkingSpot>>
}