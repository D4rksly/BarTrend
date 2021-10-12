package com.example.bartrend.domain.model

data class UserRegisterModel(
    val username: String,
    val password: String,
    val name: String,
    val email: String
)

data class UserLoginModel(
    val username: String,
    val password: String
)

data class UserModel(
    val username: String,
    val name: String,
    val email: String
)