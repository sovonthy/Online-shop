package com.example.epoxy.data.http

import com.group3.online_shop.model.Login
import com.group3.online_shop.model.Profile
import retrofit2.Response
import retrofit2.http.*

interface AppService {

    @POST("/auth/login")
    suspend fun login(
        @Body params: HashMap<String, String>
    ): Response<Login>

    @GET("/profile")
    suspend fun getProfile(
        @Header("Authorization") authorize: String,
    ): Response<Profile>
}

