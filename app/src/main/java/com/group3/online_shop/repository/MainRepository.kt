package com.group3.online_shop.repository

import android.content.Context
import com.example.epoxy.data.http.AppService
import com.group3.online_shop.model.Login
import com.group3.online_shop.utils.ResultOf
import retrofit2.Response

class MainRepository(
    private val context: Context? = null,
    private val appService: AppService? = null
) {
    suspend fun login(
        param: HashMap<String, String>
    ): ResultOf<ArrayList<Login>> {
        return safeApiCall {
            appService!!.login()
        }
    }

//    suspend fun getTodoById(id: Int): ResultOf<Todo> {
//            return safeApiCall {
//                appService!!.getTodo(id = id)
//            }
//    }


    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                ResultOf.Error(response.errorBody()?.toString() ?: "Something went wrong")
            }

        } catch (e: Exception) {
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}