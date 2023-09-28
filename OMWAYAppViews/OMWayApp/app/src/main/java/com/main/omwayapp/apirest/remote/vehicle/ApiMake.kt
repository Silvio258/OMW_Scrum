package com.main.omwayapp.apirest.remote.vehicle

import com.main.omwayapp.apirest.dto.vehicle.MakeDto
import com.main.omwayapp.apirest.model.vehicle.MakeItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiMake {

    @GET("api/make/all")
    suspend fun getAll():List<MakeItem>

    @POST("api/make/save")
    suspend fun save(@Body item: MakeDto): MakeDto

    @PUT("api/make/update")
    suspend fun update(@Body item: MakeDto): MakeDto

    @DELETE("api/make/delete/{id}")
    suspend fun delete(@Path("id") id:Int)
}