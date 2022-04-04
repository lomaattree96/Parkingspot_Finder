package com.example.parkingspot_finder.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingspot_finder.domain_.model.ParkingSpot
import com.example.parkingspot_finder.domain_.repository.ParkingspotRepository

import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: ParkingspotRepository
) :ViewModel(){
    //calling the mapstate
    var state by mutableStateOf(MapsState())

    //to start listening automatically

    init{
        viewModelScope.launch {
            repository.getParkingSpot().collectLatest {
                //when something changes this collect latest block will fire off that will give us the new spot

                //map our state to state copy
               Spots ->
                state = state.copy(
                    //list of parking spot in the state as well
                parkingspot = Spots //parking spot is our new spot
                //now go to UI layer and create these markers as composales based on our database
                )


            }
        }
    }

    fun onEvent(event:Mapsevent)
    {
        when(event) //what is that event
        {
            is Mapsevent.ToggleFallOutMap ->
            {
                //map our state to something new
                //to find the map simply go to "SNAZZY MAPS.COM" --COPY ALL THE JSON CODE
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isfalloutMap)
                            //if isfalloutmap == true -- so by clicking on toggle we set it to false so mapstyle option should be equal to null
                        {
                            null //if its currently active we deactivated it
                        }
                                else{
                            MapStyleOptions(MapsStyle.json) }, //activating it  and called this mapevent in mapscreen
                            //it is style only when the toggle faalout is actually true
                    //our properties are now our state that copy the   mapStyleOptions = MapStyleOptions(MapsStyle.json)


                    ),
                    // we want to make sure that we toggle this  --- its is fallout map enabled to state as followed map and
                    //negate it
                    isfalloutMap = !state.isfalloutMap

                )
            }
            is Mapsevent.onMapLongClick ->
            {
                //to insert a new marker
                viewModelScope.launch {
                    repository.insertParkingSpot(
                        ParkingSpot(
                            event.latlng.latitude,
                        event.latlng.longitude)
                    )
                }
            }

            is Mapsevent.infoWindowLongClick ->
            {
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)

                }
            }
        }
    }
}