package com.main.omwayapp.apirest.model.vehicle

import android.os.Parcelable
import com.main.omwayapp.apirest.model.omwayuser.DriverItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class CarItem(
    val licensePlate:String,
    val color:String,
    val year:String,
    val state:Boolean,
    /*val driver: DriverItem,*/
    val model: ModelItem
){
    constructor():this(licensePlate = "",color = "",year = "",state = true, /*driver = DriverItem(),*/model = ModelItem())
}
