package com.main.omwayapp.apirest.model.payment

import com.main.omwayapp.apirest.model.trip.RideItem

data class PaymentItem(
    val paymentId: Int,
    val total: Double,
    val paymentMethod: String,
    val ride: RideItem
)
