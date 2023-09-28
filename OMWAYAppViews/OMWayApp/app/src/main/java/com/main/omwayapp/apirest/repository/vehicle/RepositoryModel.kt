package com.main.omwayapp.apirest.repository.vehicle

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.main.omwayapp.apirest.dto.vehicle.ModelDto
import com.main.omwayapp.apirest.model.vehicle.ModelItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.vehicle.ApiModel

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryModel {
    private val apiModel: ApiModel = ApiAdapter.getInstance()
        .create(ApiModel::class.java)

    suspend fun getAll():List<ModelItem>{
        try {
            return apiModel.getAll()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<ModelItem>()
    }
    suspend fun findModelsByMake(makeId:Int):List<ModelItem>{
        try {
            return apiModel.findModelsByMake(makeId)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<ModelItem>()
    }

    suspend fun save(modelDto: ModelDto): ModelDto {
        return apiModel.save(modelDto)
    }
    suspend fun update(modelDto: ModelDto): ModelDto {
        return apiModel.update(modelDto)
    }
    suspend fun delete(id:Int){
        return apiModel.delete(id)
    }
}