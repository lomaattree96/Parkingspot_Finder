package com.example.parkingspot_finder.data_

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingspotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //used for if we want to  insert a new  part -- a new parking spot with id that already exist -- it will simply replace the old one with new one
    suspend fun insertParkingSpot(spot: ParkingspotEntity)


    @Delete
    suspend fun deleteParkingSpot(spot: ParkingspotEntity)

    //create all of our parking spots wso we get them to show them in our  map as markers -- with the help of query annotation

    @Query("SELECT * FROM parkingspotentity")
    //suspend fun is not used because it will return the flow -- so we can easily see the changes in our database
    //and flow is already asynchronous by default
    //return a flow of list of parkings
    fun getParkingSpot(): Flow<List<ParkingspotEntity>> //whenever delete or insert happen in our database so this flow will emit the list of parking spot which we can show in our map

}

//create databse class