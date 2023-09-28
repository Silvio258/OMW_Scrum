package com.main.omwayapp.apirest.repository.vehicle

import android.util.Log
import com.main.omwayapp.apirest.dto.vehicle.MakeDto
import com.main.omwayapp.apirest.model.vehicle.MakeItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.vehicle.ApiMake

class RepositoryMake {
    private val apiMake: ApiMake = ApiAdapter.getInstance()
        .create(ApiMake::class.java)

    suspend fun getAll():List<MakeItem>{
        try {
            return apiMake.getAll()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<MakeItem>()
    }

    suspend fun save(makeDto: MakeDto): MakeDto {
        return apiMake.save(makeDto)
    }
    suspend fun update(makeDto: MakeDto): MakeDto {
        return apiMake.update(makeDto)
    }
    suspend fun delete(id:Int){
        return apiMake.delete(id)
    }
}