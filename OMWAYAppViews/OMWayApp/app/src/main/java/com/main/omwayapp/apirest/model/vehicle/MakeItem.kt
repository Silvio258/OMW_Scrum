package com.main.omwayapp.apirest.model.vehicle

import android.os.Parcelable
import com.main.omwayapp.apirest.model.omwayuser.DriverItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class MakeItem(
    val id:Int,
    val name:String/*,
    val makeModels:List<ModelItem>*/
){
    constructor():this(id = 0,name = "",/*makeModels = emptyList()*/)
}
