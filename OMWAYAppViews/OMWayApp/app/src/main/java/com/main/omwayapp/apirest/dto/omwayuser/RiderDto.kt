package com.main.omwayapp.apirest.dto.omwayuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class RiderDto(
    val riderCif: String,
    val password: String,
    val name: String,
    val phone: String,
    val email:String,
    val state: Boolean
) : Parcelable
