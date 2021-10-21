package com.example.bartrend.domain.model.request

data class UserRegisterRequest(
    val email: String,
    val name: String,
    val password: String
)

data class UserLoginRequest(
    val email: String,
    val password: String
)