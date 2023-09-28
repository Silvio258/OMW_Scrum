package com.main.omwayapp.apirest.remote.omwayuser

import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.model.omwayuser.DriverItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiDriver {
    @GET("api/driver/all")
    suspend fun getAll():List<DriverItem>
    @GET("api/driver/{cif}")
    suspend fun findByCif(@Path("cif")cif:String): DriverItem

    @POST("api/driver/save")
    suspend fun save(@Body item: DriverDto): DriverDto

    @PUT("api/driver/update")
    suspend fun update(@Body item: DriverDto): DriverDto

    @DELETE("api/driver/delete/{cif}")
    suspend fun delete(@Path("cif") cif:String)
}