package com.main.omwayapp.apirest.dto.vehicle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CarDto (
    val licensePlate:String,
    val color:String,
    val year:String,
    val state:Boolean,
    val driverId:String,
    val modelId:Int
        ) : Parcelable