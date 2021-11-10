package com.group6.onlineshop.repository

import android.content.Context
import com.example.epoxy.data.http.AppService
import com.google.gson.Gson
import com.group6.onlineshop.model.Login
import com.group6.onlineshop.model.Profile
import com.group6.onlineshop.utils.ResultOf
import retrofit2.Response

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