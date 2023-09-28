package com.main.omwayapp.apirest.dto.vehicle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ModelDto(
    val id:Int,
    val name:String,
    val makeId:Int
) : Parcelable
