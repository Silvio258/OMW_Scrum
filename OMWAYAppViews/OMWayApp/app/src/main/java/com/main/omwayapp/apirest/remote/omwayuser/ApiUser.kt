package com.main.omwayapp.apirest.remote.omwayuser

import com.main.omwayapp.apirest.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUser {
    @POST("/api/rider/login")
    suspend fun getLogin(@Query("cif") cif: String, @Query("password") password :String): Response<LoginResponse>
}