package com.example.bartrend.domain.repository

import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.model.extensions.toModel
import com.example.bartrend.domain.model.extensions.toRequest
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel
import com.example.bartrend.utils.DomainResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource) {

    companion object {
        private var activeUser: UserModel? = null
    }

    fun getUser(): UserModel {
        return activeUser ?: error("User does not exist")
    }

    fun register(userRegisterModel: UserRegisterModel): DomainResponse<UserModel> {
        return loginDataSource.register(userRegisterModel.toRequest()).mapSuccess {
            val model = it.toModel()
            activeUser = model
            model
        }
    }

    fun login(userLoginModel: UserLoginModel): DomainResponse<UserModel> {
        return loginDataSource.login(userLoginModel.toRequest()).mapSuccess {
            val model = it.toModel()
            activeUser = model
            model
        }
    }

    fun checkEmailAvailability(email: String): DomainResponse<Unit> {
        return loginDataSource.checkEmailAvailability(email)
    }

    fun logout() {
        activeUser = null
    }
}