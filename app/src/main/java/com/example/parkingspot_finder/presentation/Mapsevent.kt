package com.example.parkingspot_finder.presentation


import com.example.parkingspot_finder.domain_.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

//sealed class --which specify some  kind of events that reflect the UI events that the user can do in our screen which is passed to our viewmodel
//so that viewmodel can process it in single function

sealed class Mapsevent {
    object ToggleFallOutMap : Mapsevent()


    //two events
    //set parking spot when we long click on the map
    //delete a parking spot when we long click on the map

    data class onMapLongClick(val latlng :LatLng ): Mapsevent()

    data class infoWindowLongClick(val spot: ParkingSpot):Mapsevent()
}