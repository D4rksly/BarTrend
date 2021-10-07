package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.UserLoginModel
import com.example.bartrend.domain.model.UserModel
import com.example.bartrend.domain.model.UserRegisterModel
import com.example.bartrend.utils.DomainResponse

class LoginDataSource {

    fun register(userRegisterModel: UserRegisterModel) : DomainResponse<UserModel> {
        //TODO - Implement register
        return DomainResponse.Success(UserModel(1, "Pedro", "pedroddm96@gmail.com"))
    }

    fun login(userLoginModel: UserLoginModel) : DomainResponse<UserModel> {
        //TODO - Implement login
        return DomainResponse.Success(UserModel(1, "Pedro", "pedroddm96@gmail.com"))
    }

    fun logout() : DomainResponse<Unit> {
        return DomainResponse.Success(Unit)
    }
}