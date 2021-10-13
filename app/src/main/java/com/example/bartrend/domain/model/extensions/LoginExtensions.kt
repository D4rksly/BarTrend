package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.UserLoginRequest
import com.example.bartrend.domain.model.UserModelResponse
import com.example.bartrend.domain.model.UserRegisterRequest
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel

fun UserRegisterModel.toRequest() = UserRegisterRequest(
    username, password, name, email
)

fun UserLoginModel.toRequest() = UserLoginRequest(
    username, password
)

fun UserModelResponse.toModel() = UserModel(
    username, name, email
)