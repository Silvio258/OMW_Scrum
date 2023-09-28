package com.main.omwayapp.apirest.repository.omwayuser

import android.util.Log
import com.main.omwayapp.apirest.remote.ApiAdapter
import com.main.omwayapp.apirest.remote.omwayuser.ApiUser
import com.main.omwayapp.apirest.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import retrofit2.Response

class RepositoryUser : CoroutineScope by MainScope() {
    val apiUser : ApiUser = ApiAdapter.getInstance()
        .create(ApiUser::class.java)

    suspend fun fetchData(cif: String, password: String): Result<LoginResponse> {
        var  loginResponse : LoginResponse = LoginResponse()
        return try{
            val response: Response<LoginResponse> = apiUser.getLogin(cif, password)
            loginResponse = response.body() as LoginResponse
            Log.d("RESULTADO OK","RESULTADO OK, $loginResponse.msg")
            Result.success(loginResponse)

        } catch (e: Exception) {
            Log.d("ERRORCUSTOM", "$e.message")
            Result.failure(e)
        }
    }
}