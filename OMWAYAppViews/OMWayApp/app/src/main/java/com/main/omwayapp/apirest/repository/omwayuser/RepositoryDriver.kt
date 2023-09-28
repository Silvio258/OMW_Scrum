package com.main.omwayapp.apirest.repository.omwayuser

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.model.omwayuser.DriverItem
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.omwayuser.ApiDriver
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryDriver {


    private val apiDriver: ApiDriver = ApiAdapter.getInstance()
        .create(ApiDriver::class.java)

    // ApiAdapter.getInstance() devuelve la instancia de retrofit con la URL de la api
    // create(ApiDriver::class.java) es para crear una implementación de la interfaz ApiDriver en la instancia de retrofit
    //luego esa instancia del api de driver se asigna a la variable apiDriver
    // y se puede acceder a los metodos con la ruta de la api que estan definidos en la interfaz
    //De esta forma obtenemos los datos o enviamos los datos.

    suspend fun getAll():List<DriverItem>{
        try {
            return apiDriver.getAll()
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
        return emptyList<DriverItem>()
    }
    suspend fun findByCif(cif:String): DriverItem {
        return apiDriver.findByCif(cif)
    }

    suspend fun save(driverDto: DriverDto): DriverDto {
        return apiDriver.save(driverDto)
    }
    suspend fun update(driverDto: DriverDto): DriverDto {
        return apiDriver.update(driverDto)
    }
    suspend fun delete(cif:String){
        return apiDriver.delete(cif)
    }
}