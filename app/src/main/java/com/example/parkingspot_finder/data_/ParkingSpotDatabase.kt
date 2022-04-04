package com.example.parkingspot_finder.data_

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ParkingspotEntity::class],
    version = 1 //whenever something is changed we need to change this version so that room knows that something is changed
)
abstract class ParkingspotDatabase : RoomDatabase()  //room just basically assign the values from behind
{

    abstract val dao: ParkingspotDao //accessing the dao in the database class
}

//presentation layer shoudlnot have access either to the database  , dao or entity