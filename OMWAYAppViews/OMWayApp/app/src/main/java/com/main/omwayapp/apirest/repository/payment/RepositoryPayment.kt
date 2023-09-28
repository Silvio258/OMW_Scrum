package com.main.omwayapp.apirest.repository.payment

import com.main.omwayapp.apirest.dto.payment.PaymentDto
import com.main.omwayapp.apirest.model.payment.PaymentItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.payment.ApiPayment

class RepositoryPayment {
    private val apiPayment: ApiPayment = ApiAdapter.getInstance()
        .create(ApiPayment::class.java)

    suspend fun findPaymentById(id:Int): PaymentItem {
        return apiPayment.findCashById(id)
    }

    suspend fun save(paymentDto: PaymentDto): PaymentDto {
        return apiPayment.save(paymentDto)
    }
    suspend fun update(paymentDto: PaymentDto): PaymentDto {
        return apiPayment.update(paymentDto)
    }
}