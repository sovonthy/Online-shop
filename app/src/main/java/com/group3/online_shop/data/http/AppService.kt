package com.example.epoxy.data.http

import com.group3.online_shop.model.Login
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppService {

    @POST("auth/login")
    suspend fun login(): Response<ArrayList<Login>>

    @GET("/todos/{id}")
    suspend fun getTodo(
        @Path("id") id: Int
    ): Response<Login>
}
