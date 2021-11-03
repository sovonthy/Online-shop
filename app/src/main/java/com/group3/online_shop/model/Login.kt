package com.group3.online_shop.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Login(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,
)