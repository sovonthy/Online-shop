package com.group3.online_shop.repository

import android.content.Context
import com.example.epoxy.data.http.AppService
import com.google.gson.Gson
import com.group3.online_shop.model.Login
import com.group3.online_shop.model.Profile
import com.group3.online_shop.utils.ResultOf
import retrofit2.Response
import retrofit2.http.Header

class MainRepository(
    private val context: Context? = null,
    private val appService: AppService? = null
) {
    suspend fun login(
        param: HashMap<String, String>
    ): ResultOf<Login> {
        return safeApiCall {
            appService!!.login(params = param)
        }
    }

    suspend fun getProfile(
        authorize: String
    ): ResultOf<Profile> {
        return  safeApiCall {
            appService!!.getProfile(authorize = authorize)
        }
    }




    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {

                println("body = " +  Gson().toJson(response.code()))
                ResultOf.Error(response.code().toString() ?: "Something went wrong")
            }

        } catch (e: Exception) {
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}