package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.request.UserLoginRequest
import com.example.bartrend.domain.model.request.UserRegisterRequest
import com.example.bartrend.domain.model.response.UserModelResponse
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel

fun UserRegisterModel.toRequest() = UserRegisterRequest(
    email, name, password
)

fun UserLoginModel.toRequest() = UserLoginRequest(
    email, password
)

fun UserModelResponse.toModel() = UserModel(
    id, email, name
)