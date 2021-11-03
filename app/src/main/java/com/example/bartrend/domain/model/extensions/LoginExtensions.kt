package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.request.UserLoginRequest
import com.example.bartrend.domain.model.request.UserRegisterRequest
import com.example.bartrend.domain.model.response.UserModelResponse
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel

fun UserRegisterModel.toRequest() = UserRegisterRequest(
    email = email,
    name = name,
    password = password
)

fun UserLoginModel.toRequest() = UserLoginRequest(
    email = email,
    password = password
)

fun UserModelResponse.toModel() = UserModel(
    id = id,
    email = email,
    name = name
)