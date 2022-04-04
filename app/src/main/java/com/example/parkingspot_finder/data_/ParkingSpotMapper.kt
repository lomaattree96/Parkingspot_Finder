package com.example.parkingspot_finder.data_

import com.example.parkingspot_finder.domain_.model.ParkingSpot


fun ParkingspotEntity.toParkingspot() : ParkingSpot {

    return ParkingSpot(
        lat =lat, //values from our entity
        lng = lng,
        id = id
    )

}

fun ParkingSpot.toParkingspotEntity() :ParkingspotEntity{
    return ParkingspotEntity(
        lat = lat,
        lng = lng,
        id =id
    )

}

//define the repository to call the dao to access our local database -- in the domain layer
// we have interface that will deifne the functions of our repository that needs to be implemented