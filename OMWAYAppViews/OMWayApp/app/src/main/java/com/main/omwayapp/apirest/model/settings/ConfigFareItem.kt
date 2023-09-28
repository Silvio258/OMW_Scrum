package com.main.omwayapp.apirest.model.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class ConfigFareItem(
    val id:Int,
    val fare:Double,
    val name:String,
    val state:Boolean
)
