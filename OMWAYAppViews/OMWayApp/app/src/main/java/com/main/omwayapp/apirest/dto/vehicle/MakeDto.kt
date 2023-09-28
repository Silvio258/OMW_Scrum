package com.main.omwayapp.apirest.dto.vehicle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MakeDto(
    val id:Int,
    val name:String
) : Parcelable
