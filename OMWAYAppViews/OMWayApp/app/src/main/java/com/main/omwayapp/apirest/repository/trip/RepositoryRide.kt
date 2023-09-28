package com.main.omwayapp.apirest.repository.trip

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.main.omwayapp.apirest.dto.trip.RideDto
import com.main.omwayapp.apirest.model.trip.RideItem
import com.main.omwayapp.apirest.model.vehicle.ModelItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.trip.ApiRide

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryRide {
    private val apiRide: ApiRide = ApiAdapter.getInstance()
        .create(ApiRide::class.java)

    suspend fun findInProgressRidesByRiderCif(cif:String):List<RideItem>{
        try {
            return apiRide.findInProgressRidesByRiderCif(cif)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RideItem>()
    }
    suspend fun findDiscontinuedRideByRiderCif(cif:String):List<RideItem>{
        try {
            return apiRide.findDiscontinuedRideByRiderCif(cif)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RideItem>()
    }
    suspend fun findInProgressRidesByDriverCif(cif:String):List<RideItem>{
        try {
            return apiRide.findInProgressRidesByDriverCif(cif)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RideItem>()
    }
    suspend fun findDiscontinuedRidesByDriverCif(cif:String):List<RideItem>{
        try {
            return apiRide.findDiscontinuedRidesByDriverCif(cif)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RideItem>()
    }
    suspend fun getRequestedRides():List<RideItem>{
        try {
            return apiRide.getRequestedRides()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RideItem>()
    }

    suspend fun save(rideDto: RideDto): RideDto {
        return apiRide.save(rideDto)
    }
    suspend fun update(rideDto: RideDto): RideDto {
        return apiRide.update(rideDto)
    }
}