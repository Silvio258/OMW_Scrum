package com.main.omwayapp.apirest.dto.trip

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.sql.Time
import java.time.LocalDate

@Parcelize
@Serializable
data class RideDto(
    val id:Int?,
    val pickUpTime: String,
    val dropOffTime: String?,
    val pickUpLocation:String,
    val dropOffLocation:String,
    val distance:Double,
    val date: String,
    val notes:String,
    val state: String,
    val fare: Double,
    val rating:Int?,
    val comment:String?,
    val riderId: String,
    val driverId:String?,
    val carId:String?
) : Parcelable
