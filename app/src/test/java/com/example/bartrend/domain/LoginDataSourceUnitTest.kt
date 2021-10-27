package com.example.bartrend.domain

import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.utils.Connector
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class LoginDataSourceUnitTest {

     private lateinit var loginDataSource: LoginDataSource

     @Before
     fun setup() {
         MockKAnnotations.init(this, relaxUnitFun = true)
         Connector.strictPolicyEnabled = false

         loginDataSource = LoginDataSource()
     }

     @Test
     fun `when checking email availability, given a nonexistent email, return success`() {
         //Arrange
         val mockedEmail = "fake@fake.fake"

         //Act
         val response = loginDataSource.checkEmailAvailability(mockedEmail)

         //Assert
         assert(response.isSuccess())
     }

     @Test
     fun `when checking email availability, given a existent email, return error`() {
        //Arrange
        val mockedEmail = "pedroddm96@gmail.com"

        //Act
        val response = loginDataSource.checkEmailAvailability(mockedEmail)

        //Assert
        assert(!response.isSuccess())
    }
}