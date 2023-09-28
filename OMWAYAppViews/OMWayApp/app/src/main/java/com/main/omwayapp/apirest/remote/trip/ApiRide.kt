package com.main.omwayapp.apirest.remote.trip

import com.main.omwayapp.apirest.dto.trip.RideDto
import com.main.omwayapp.apirest.model.trip.RideItem
import com.main.omwayapp.apirest.model.vehicle.ModelItem
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiRide {

    @GET("api/ride/InProgressRidesRider/{cif}")
    suspend fun findInProgressRidesByRiderCif(@Path("cif")cif:String):List<RideItem>
    @GET("api/ride/DiscontinuedRidesRider/{cif}")
    suspend fun findDiscontinuedRideByRiderCif(@Path("cif")cif:String):List<RideItem>
    @GET("api/ride/InProgressRidesDriver/{cif}")
    suspend fun findInProgressRidesByDriverCif(@Path("cif")cif:String):List<RideItem>
    @GET("api/ride/DiscontinuedRidesDriver/{cif}")
    suspend fun findDiscontinuedRidesByDriverCif(@Path("cif")cif:String):List<RideItem>
    @GET("api/ride/rideRequested")
    suspend fun getRequestedRides():List<RideItem>

    @POST("api/ride/save")
    suspend fun save(@Body item: RideDto): RideDto

    @PUT("api/ride/update")
    suspend fun update(@Body item: RideDto): RideDto
}