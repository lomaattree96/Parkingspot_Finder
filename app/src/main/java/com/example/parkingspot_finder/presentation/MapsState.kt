package com.example.parkingspot_finder.presentation




import com.example.parkingspot_finder.domain_.model.ParkingSpot
import com.google.maps.android.compose.MapProperties

data class MapsState(
    //to change some map specific  configuration
    val properties: MapProperties = MapProperties(
        // mapStyleOptions = MapStyleOptions(MapsStyle.json) //we just wanted to show this so it should be used in viewmodel
    ),

//to show the fallout map  -- boolean
    val isfalloutMap: Boolean = false,

    //specified this style in our map properties == so we can choose map style options
val parkingspot: List<ParkingSpot> = emptyList()




)

//go to mapsscreen