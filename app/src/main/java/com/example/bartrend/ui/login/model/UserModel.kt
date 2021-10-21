package com.example.bartrend.ui.login.model

data class UserRegisterModel(
    val email: String,
    val name: String,
    val password: String
)

data class UserLoginModel(
    val email: String,
    val password: String
)

data class UserModel(
    val email: String,
    val name: String
)