package com.example.bartrend.domain.model

data class UserRegisterRequest(
    val username: String,
    val password: String,
    val name: String,
    val email: String
)

data class UserLoginRequest(
    val username: String,
    val password: String
)