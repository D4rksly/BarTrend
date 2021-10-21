package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.request.UserLoginRequest
import com.example.bartrend.domain.model.request.UserRegisterRequest
import com.example.bartrend.domain.model.response.UserModelResponse
import com.example.bartrend.utils.Connector
import utils.DomainResponse
import utils.MD5
import java.lang.NullPointerException
import java.sql.SQLIntegrityConstraintViolationException

class LoginDataSource {

    companion object {
        const val USERS_TABLE = "Users"
    }

    fun register(userRegisterModel: UserRegisterRequest): DomainResponse<UserModelResponse> {
        return try {

            Connector.insert(USERS_TABLE, hashMapOf(
                "email" to userRegisterModel.email,
                "name" to userRegisterModel.name,
                "password" to MD5.encrypt(userRegisterModel.password)
            ))

            DomainResponse.Success(
                UserModelResponse(
                    email = userRegisterModel.email,
                    name = userRegisterModel.name
                )
            )
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        } catch (ex: SQLIntegrityConstraintViolationException) {
            DomainResponse.Error("Email already Exists")
        }
    }

    fun login(userLoginModel: UserLoginRequest): DomainResponse<UserModelResponse> {
        try {
            val result = Connector.select(
                USERS_TABLE, hashMapOf(
                    "email" to userLoginModel.email,
                    "password" to MD5.encrypt(userLoginModel.password)
                )
            )

            return if(result.next()) {
                DomainResponse.Success(
                    UserModelResponse(
                        email = result.getString("email"),
                        name = result.getString("name")
                    )
                )
            } else {
                DomainResponse.Error("Email or Password Invalid")
            }
        } catch (ex: NullPointerException) {
            return DomainResponse.Error("Connection Failed")
        }
    }

    fun checkEmailAvailability(email: String): DomainResponse<String> {
        return try {
            val result = Connector.select(USERS_TABLE, Pair("email", email))

            if(result.next()) {
                DomainResponse.Error("Email already registered")
            } else {
                DomainResponse.Success("Email Available")
            }
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}