package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.UserLoginRequest
import com.example.bartrend.domain.model.UserModelResponse
import com.example.bartrend.domain.model.UserRegisterRequest
import com.example.bartrend.utils.Connector
import kotlinx.coroutines.coroutineScope
import utils.DomainResponse
import utils.MD5
import java.lang.NullPointerException
import java.sql.ResultSet
import java.sql.SQLIntegrityConstraintViolationException
import kotlin.coroutines.coroutineContext

class LoginDataSource {

    companion object {
        const val USERS_TABLE = "Users"
    }

    fun register(userRegisterModel: UserRegisterRequest): DomainResponse<UserModelResponse> {
        return try {

            Connector.insert(USERS_TABLE, hashMapOf(
                "username" to userRegisterModel.username,
                "password" to MD5.encrypt(userRegisterModel.password),
                "name" to userRegisterModel.name,
                "email" to userRegisterModel.email
            ))

            DomainResponse.Success(
                UserModelResponse(
                    username = userRegisterModel.username,
                    name = userRegisterModel.name,
                    email = userRegisterModel.email
                )
            )
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        } catch (ex: SQLIntegrityConstraintViolationException) {
            DomainResponse.Error("Username already Exists")
        }
    }

    fun login(userLoginModel: UserLoginRequest): DomainResponse<UserModelResponse> {
        try {
            val result = Connector.select(
                USERS_TABLE, hashMapOf(
                    "username" to userLoginModel.username,
                    "password" to MD5.encrypt(userLoginModel.password)
                )
            )

            return if(result.next()) {
                DomainResponse.Success(
                    UserModelResponse(
                        username = result.getString("username"),
                        name = result.getString("name"),
                        email = result.getString("email")
                    )
                )
            } else {
                DomainResponse.Error("Username or Password Invalid")
            }
        } catch (ex: NullPointerException) {
            return DomainResponse.Error("Connection Failed")
        }
    }

    fun checkUsernameAvailability(username: String): DomainResponse<String> {
        return try {
            val result = Connector.select(USERS_TABLE, Pair("username", username))

            if(result.next()) {
                DomainResponse.Error("Username already exists")
            } else {
                DomainResponse.Success("Username Available")
            }
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}