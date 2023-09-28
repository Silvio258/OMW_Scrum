package com.main.omwayapp.apirest.remote.vehicle

import com.main.omwayapp.apirest.dto.vehicle.CarDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiCar {
    @POST("api/car/save")
    suspend fun save(@Body item: CarDto): CarDto

    @PUT("api/car/update")
    suspend fun update(@Body item: CarDto): CarDto

    @DELETE("api/car/delete/{id}")
    suspend fun delete(@Path("id") id:String)
}