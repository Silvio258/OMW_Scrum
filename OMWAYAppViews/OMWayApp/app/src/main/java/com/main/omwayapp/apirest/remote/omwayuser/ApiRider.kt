package com.main.omwayapp.apirest.remote.omwayuser

import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.model.omwayuser.RiderItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiRider {
    @GET("api/rider/all")
    suspend fun getAll():List<RiderItem>

    @GET("api/rider/{cif}")
    suspend fun findByCif(@Path("cif")cif:String): RiderItem

    @POST("api/rider/save")
    suspend fun save(@Body item: RiderDto): RiderDto

    @PUT("api/rider/update")
    suspend fun update(@Body item: RiderDto): RiderDto

    @DELETE("api/rider/delete/{cif}")
    suspend fun delete(@Path("cif") cif:String)
}