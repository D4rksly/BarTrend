package com.example.bartrend.domain.model

import java.util.*

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
    val username: Int,
    val name: String,
    val email: String
)