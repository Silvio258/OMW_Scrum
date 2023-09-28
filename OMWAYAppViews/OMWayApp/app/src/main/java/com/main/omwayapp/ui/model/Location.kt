package com.main.omwayapp.ui.model

import com.google.android.gms.maps.model.LatLng

data class Location(val coordinate: LatLng,
                    val title:String,
                    val description:String)
