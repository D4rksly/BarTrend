package com.example.bartrend.domain.repository

import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.model.UserLoginModel
import com.example.bartrend.domain.model.UserModel
import com.example.bartrend.domain.model.UserRegisterModel
import com.example.bartrend.utils.DomainResponse

class LoginRepository(private val loginDataSource: LoginDataSource) {

    fun register(userRegisterModel: UserRegisterModel): DomainResponse<UserModel> {
        return loginDataSource.register(userRegisterModel)
    }

    fun login(userLoginModel: UserLoginModel): DomainResponse<UserModel> {
        return loginDataSource.login(userLoginModel)
    }

    fun logout(): DomainResponse<Unit> {
        return loginDataSource.logout()
    }
}