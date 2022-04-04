package com.example.parkingspot_finder.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable



//to have  better understanding of viewmodel and state mapsstate

//Inside scaffold we need googlemap fill our whole screen with the help of googlemap
//@SuppressLint("RememberReturnType")

@Composable
fun MapsScreen(
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()

    //we dont change the way we display the UI so UISettings can be made remember variable
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false) //to control zoom control -- should be right bottom corner -- to not overlap the FAB

    }
    Scaffold(scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(Mapsevent.ToggleFallOutMap) //call our map in our main activity
            }) {
                //setting icons acc to whether fallout map is toggled or not

                Icon(
                    imageVector = if (viewModel.state.isfalloutMap) //if we are in our map it needs to be toggled off
                    {
                        Icons.Default.ToggleOff
                    } else {
                        Icons.Default.ToggleOn
                    },
                    contentDescription = "Toggle FallOut Map"
                )
            }
        }
    )
    {

        //Inside scaffold we need googlemap fill our whole screen with the help of googlemap
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            //to set map properties object --whenever the properties are updated in our remodel then our google map will also recompose and reflect the new state
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            //on map long click -- we want to set the marker later for our parking spot which gives latitude and longitutde object
            onMapLongClick = {

                viewModel.onEvent(Mapsevent.onMapLongClick(it))
                //simply used it to send those coordinates to the viewmodel --saved in our room database --which is used here to simply saved the marker
            }
            //(zoomControlsEnabled = false) //to control zoom control -- should be right bottom corner -- to not overlap the FAB
        ) {
            //can put bunch of composables that modify our map that draw on our map such as  amrkers for example

            viewModel.state.parkingspot.forEach{
                   spot ->
                Marker(position = LatLng(spot.lat, spot.lng),
                title = "Parking Spot (${spot.lat},${spot.lng})",//title of the infowindow when we click  on the marker
               snippet = "Long Click to delete",
                    //that is a simple text which gives user information

                onInfoWindowClick = {
                    //when we click on the marker then we want to show this info window
                    viewModel.onEvent(Mapsevent.infoWindowLongClick(spot))
                },
                    //gives access to this marker
                    onClick = {
                        it.showInfoWindow()
                        true //to handle the click event
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        //map bitmap descriptor factory
                    BitmapDescriptorFactory.HUE_ROSE
                    //GOTO MAIN ACTIVITY
                    )//to change the color here

                )
            }


        }

    }

}