package com.main.omwayapp.apirest.model.vehicle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class ModelItem(
    val id:Int,
    val name:String,
    val make: MakeItem/*,
    val modelCars:List<CarItem>*/

){
    constructor():this(id = 0,name = "",make = MakeItem()/*,modelCars=emptyList()*/)
}
