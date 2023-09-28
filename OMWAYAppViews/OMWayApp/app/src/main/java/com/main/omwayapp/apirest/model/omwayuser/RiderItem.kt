package com.main.omwayapp.apirest.model.omwayuser

import android.os.Parcelable
import com.main.omwayapp.apirest.model.trip.RideItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class RiderItem(
    var cif: String,
    var password: String,
    var name: String,
    var phone: String,
    var email:String,
    var state: Boolean,
    var riderRides: List<RideItem>
){
    constructor():this(cif = "",password = "",name = "",phone = "",email = "",state = true, riderRides = emptyList())
}
