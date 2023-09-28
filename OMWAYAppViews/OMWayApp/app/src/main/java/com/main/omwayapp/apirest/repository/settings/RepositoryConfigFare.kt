package com.main.omwayapp.apirest.repository.settings

import android.util.Log
import com.main.omwayapp.apirest.model.settings.ConfigFareItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.settings.ApiConfigFare
import java.lang.Exception

class RepositoryConfigFare {
    private val apiConfigFare: ApiConfigFare = ApiAdapter.getInstance()
        .create(ApiConfigFare::class.java)

    suspend fun getAll():List<ConfigFareItem>{
        try {
            return apiConfigFare.getAll()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<ConfigFareItem>()
    }

    suspend fun save(configFareItem: ConfigFareItem): ConfigFareItem {
        return apiConfigFare.save(configFareItem)
    }
    suspend fun update(configFareItem: ConfigFareItem): ConfigFareItem {
        return apiConfigFare.update(configFareItem)
    }
    suspend fun delete(id:Int){
        return apiConfigFare.delete(id)
    }
}