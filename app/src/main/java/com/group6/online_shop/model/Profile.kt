package com.group6.online_shop.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerializedName("username")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("avatar")
    val image: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("status")
    val status: String,
)