package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.request.UserLoginRequest
import com.example.bartrend.domain.model.request.UserRegisterRequest
import com.example.bartrend.domain.model.response.UserModelResponse
import com.example.bartrend.utils.Connector
import com.example.bartrend.utils.DomainResponse
import com.example.bartrend.utils.extensions.toSHA256
import java.lang.NullPointerException
import java.sql.SQLIntegrityConstraintViolationException
import javax.inject.Inject

class LoginDataSource @Inject constructor() {

    companion object {
        private const val USERS_TABLE = "Users"
    }

    fun register(userRegisterModel: UserRegisterRequest): DomainResponse<UserModelResponse> {
        return try {

            Connector.insert(USERS_TABLE, hashMapOf(
                "email" to userRegisterModel.email,
                "name" to userRegisterModel.name,
                "password" to userRegisterModel.password.toSHA256()
            ))

            val result = Connector.select(USERS_TABLE, Pair("email", userRegisterModel.email))

            if(result.next()) {
                DomainResponse.Success(
                    UserModelResponse(
                        id = result.getInt("id"),
                        email = userRegisterModel.email,
                        name = userRegisterModel.name
                    )
                )
            } else {
                DomainResponse.Error("Id was not created")
            }
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
                    "password" to userLoginModel.password.toSHA256()
                )
            )

            return if(result.next()) {
                DomainResponse.Success(
                    UserModelResponse(
                        id = result.getInt("id"),
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

    fun checkEmailAvailability(email: String): DomainResponse<Unit> {
        return try {
            val result = Connector.select(USERS_TABLE, Pair("email", email))

            if(result.next()) {
                DomainResponse.Error("Email already registered")
            } else {
                DomainResponse.Success(Unit)
            }
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}