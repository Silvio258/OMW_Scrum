package com.main.omwayapp.apirest.remote.settings

import com.main.omwayapp.apirest.model.settings.ConfigFareItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiConfigFare {

    @GET("api/configFare/all")
    suspend fun getAll():List<ConfigFareItem>

    @POST("api/configFare/save")
    suspend fun save(@Body item: ConfigFareItem): ConfigFareItem

    @PUT("api/configFare/update")
    suspend fun update(@Body item: ConfigFareItem): ConfigFareItem

    @DELETE("api/configFare/delete/{id}")
    suspend fun delete(@Path("id") id:Int)
}