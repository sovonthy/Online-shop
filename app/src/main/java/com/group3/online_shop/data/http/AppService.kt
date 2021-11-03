package com.example.epoxy.data.http

import com.group3.online_shop.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {

    @GET("/users")
    suspend fun login(): Response<ArrayList<User>>

    @GET("/todos/{id}")
    suspend fun getTodo(
        @Path("id") id: Int
    ): Response<User>
}

