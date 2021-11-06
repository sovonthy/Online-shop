package com.group3.online_shop.model

import kotlinx.serialization.Serializable

@Serializable
data class Login (
    val user: User,
    val token: Token
)

@Serializable
data class Token (
    val accessToken: String,
    val tokenType: String,
    val expiresIn: ExpiresIn,
    val refreshToken: String,
    val type: String
)

@Serializable
class ExpiresIn()

@Serializable
data class User (
    val username: String,
    val email: String,
    val avatar: String,
    val phone: Long,
    val status: String
)
