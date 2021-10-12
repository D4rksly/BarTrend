package com.example.bartrend.domain.repository

import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.model.UserLoginModel
import com.example.bartrend.domain.model.UserModel
import com.example.bartrend.domain.model.UserRegisterModel
import utils.DomainResponse

class LoginRepository(private val loginDataSource: LoginDataSource) {

    var activeUser: UserModel? = null

    fun register(userRegisterModel: UserRegisterModel): DomainResponse<UserModel> {
        return loginDataSource.register(userRegisterModel).success {
            activeUser = it
        }
    }

    fun login(userLoginModel: UserLoginModel): DomainResponse<UserModel> {
        return loginDataSource.login(userLoginModel).success {
            activeUser = it
        }
    }

    fun checkUsernameAvailability(username: String): DomainResponse<String> {
        return loginDataSource.checkUsernameAvailability(username)
    }

    fun logout() {
        activeUser = null
    }
}