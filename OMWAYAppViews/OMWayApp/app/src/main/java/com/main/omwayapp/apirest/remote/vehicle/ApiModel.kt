package com.main.omwayapp.apirest.remote.vehicle

import com.main.omwayapp.apirest.dto.vehicle.ModelDto
import com.main.omwayapp.apirest.model.vehicle.ModelItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiModel {
    @GET("api/model/all")
    suspend fun getAll():List<ModelItem>

    @GET("api/model/modelsByMake/{makeId}")
    suspend fun findModelsByMake(@Path("makeId")makeId:Int):List<ModelItem>

    @POST("api/model/save")
    suspend fun save(@Body item: ModelDto): ModelDto

    @PUT("api/model/update")
    suspend fun update(@Body item: ModelDto): ModelDto

    @DELETE("api/model/delete/{id}")
    suspend fun delete(@Path("id") id:Int)
}