package com.example.parkingspot_finder.data_

import androidx.room.Entity
import androidx.room.PrimaryKey

//database -- where we will store our simple parking spot where the user actually put their car


@Entity
data class ParkingspotEntity(
    val lat: Double,
    val lng: Double,//cant take long because room database took java code where long is a keyword so it will give error
    @PrimaryKey val id: Int? = null
)

//create dao object  for parking spot -- access object in which we define where we have to basically insert new parking spot
//basically an interface between our app and our room database