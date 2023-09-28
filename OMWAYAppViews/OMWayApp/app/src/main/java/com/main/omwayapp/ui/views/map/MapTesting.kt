package com.main.omwayapp.ui.views.map

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.model.Location

//import com.main.omwayapp.Location


@Composable
fun rememberMapLifeCycle(map: MapView): LifecycleObserver {
    return remember {
        LifecycleEventObserver{source,event->
            when(event){
                Lifecycle.Event.ON_CREATE->map.onCreate(Bundle())
                Lifecycle.Event.ON_START->map.onStart()
                Lifecycle.Event.ON_RESUME->map.onResume()
                Lifecycle.Event.ON_PAUSE->map.onPause()
                Lifecycle.Event.ON_STOP->map.onPause()
                Lifecycle.Event.ON_DESTROY->map.onDestroy()
                Lifecycle.Event.ON_ANY->throw IllegalStateException()
            }
        }
    }
}



@Composable
fun OurGoogleMaps(location: Location, onReady:(GoogleMap)->Unit){
    //Map
    val context= LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle= LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(rememberMapLifeCycle(map = mapView))
    //Map
    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync { googleMap ->
                val zoomLevel=18f
                val uam= LatLng(12.10877952,-86.2564972)
                googleMap.moveCamera(
                    CameraUpdateFactory.
                    newLatLngZoom(location.coordinate,zoomLevel))
                googleMap.addMarker(
                    MarkerOptions().
                    position(location.coordinate).
                    title(location.title).
                    snippet(location.description))
                //googleMap.mapType=2
            }
        }
    })

}




