package com.main.omwayapp.apirest.model.omwayuser

import java.time.LocalDate
import android.os.Parcelable
import com.main.omwayapp.apirest.model.trip.RideItem
import com.main.omwayapp.apirest.model.vehicle.CarItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


data class DriverItem(
    val cif: String,
    val password: String,
    val name: String,
    val phone: String,
    val email:String,
    val state: Boolean,
    val dlExpirationDate:LocalDate?,
    val sumRating:Int,
    val numberRides:Int,
    val driverCars: List<CarItem>,
    /*val driverRides:List<RideItem>*/

){
    constructor():this(cif = "",password = "",name = "",phone = "",email = "",state = true,dlExpirationDate = null,sumRating = 0,numberRides = 0, driverCars = emptyList()/*,driverRides = emptyList()*/)
}
