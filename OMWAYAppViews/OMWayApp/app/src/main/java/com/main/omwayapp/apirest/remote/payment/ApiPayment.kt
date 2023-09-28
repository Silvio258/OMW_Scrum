package com.main.omwayapp.apirest.remote.payment

import com.main.omwayapp.apirest.dto.payment.PaymentDto
import com.main.omwayapp.apirest.model.payment.PaymentItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiPayment {
    @GET("api/payment/all")
    suspend fun getAll():List<PaymentItem>

    @GET("api/payment/{id}")
    suspend fun findCashById(@Path("id")id:Int): PaymentItem

    @POST("api/payment/save")
    suspend fun save(@Body item: PaymentDto): PaymentDto

    @PUT("api/payment/update")
    suspend fun update(@Body item: PaymentDto): PaymentDto
}