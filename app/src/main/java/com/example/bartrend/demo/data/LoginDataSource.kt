package com.example.bartrend.demo.data

import com.example.bartrend.demo.data.model.LoggedInUser
import utils.DomainResponse

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): DomainResponse<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return DomainResponse.Success(fakeUser)
        } catch (e: Throwable) {
            return DomainResponse.Error("Error logging in")
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}