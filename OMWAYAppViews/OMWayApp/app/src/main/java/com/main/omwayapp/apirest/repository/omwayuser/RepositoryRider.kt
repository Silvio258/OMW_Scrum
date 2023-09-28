package com.main.omwayapp.apirest.repository.omwayuser

import android.util.Log
import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.model.omwayuser.RiderItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.omwayuser.ApiRider

class RepositoryRider {
    private val apiRider: ApiRider = ApiAdapter.getInstance()
        .create(ApiRider::class.java)

    suspend fun getAll():List<RiderItem>{
        try {
            return apiRider.getAll()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<RiderItem>()
    }
    suspend fun findByCif(cif:String): RiderItem {
        return apiRider.findByCif(cif)
    }

    suspend fun save(riderDto: RiderDto): RiderDto {
        return apiRider.save(riderDto)
    }
    suspend fun update(riderDto: RiderDto): RiderDto {
        return apiRider.update(riderDto)
    }
    suspend fun delete(cif:String){
        return apiRider.delete(cif)
    }
}