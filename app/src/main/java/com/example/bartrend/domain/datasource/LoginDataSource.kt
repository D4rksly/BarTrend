package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.UserLoginModel
import com.example.bartrend.domain.model.UserModel
import com.example.bartrend.domain.model.UserRegisterModel
import com.example.bartrend.utils.Connector
import utils.DomainResponse
import utils.MD5
import java.sql.SQLIntegrityConstraintViolationException

class LoginDataSource {

    companion object {
        const val USERS_TABLE = "Users"
    }

    fun register(userRegisterModel: UserRegisterModel): DomainResponse<UserModel> {
        return try {
            Connector.insert(USERS_TABLE, hashMapOf(
                "username" to userRegisterModel.username,
                "password" to MD5.encrypt(userRegisterModel.password),
                "name" to userRegisterModel.name,
                "email" to userRegisterModel.email
            ))

            DomainResponse.Success(
                UserModel(
                    username = userRegisterModel.username,
                    name = userRegisterModel.name,
                    email = userRegisterModel.email
                )
            )
        } catch (ex: SQLIntegrityConstraintViolationException) {
            DomainResponse.Error("Username already Exists")
        }
    }

    fun login(userLoginModel: UserLoginModel): DomainResponse<UserModel> {
        val result = Connector.select(USERS_TABLE, hashMapOf(
            "username" to userLoginModel.username,
            "password" to MD5.encrypt(userLoginModel.password)
        ))

        return if(result.next()) {
            DomainResponse.Success(
                UserModel(
                    username = result.getString("username"),
                    name = result.getString("name"),
                    email = result.getString("email")
                )
            )
        } else {
            DomainResponse.Error("Username or Password Invalid")
        }
    }

    fun checkUsernameAvailability(username: String): DomainResponse<String> {
        val result = Connector.select(USERS_TABLE, Pair("username", username))

        return if(result.next()) {
            DomainResponse.Error("Username already exists")
        } else {
            DomainResponse.Success("Username Available")
        }
    }
}