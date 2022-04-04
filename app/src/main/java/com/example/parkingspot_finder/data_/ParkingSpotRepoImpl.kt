package com.example.parkingspot_finder.data_


import com.example.parkingspot_finder.domain_.model.ParkingSpot
import com.example.parkingspot_finder.domain_.repository.ParkingspotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpot_repo_impl(
    private val dao: ParkingspotDao
) : ParkingspotRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingspotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingspotEntity())
    }

    override fun getParkingSpot(): Flow<List<ParkingSpot>> {
        return dao.getParkingSpot().map {
            //map which gives us the list of parking spot entities
                spots ->
            spots.map{it.toParkingspot()}
        }
        //ready to setup dagger hilt for dependancy injection to inject dependancy in  our viewmodel --parking spot application
    }
}