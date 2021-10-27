package com.example.bartrend.domain

import com.example.bartrend.utils.Connector
import io.mockk.MockKAnnotations
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ConnectorUnitTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        Connector.strictPolicyEnabled = false
    }

    @Test
    fun `Check database connection`() {
        //Arrange
        val mockedQuery = "Select 'connection test'"

        //Act
        try {
            Connector.executeQuery(mockedQuery)
            assert(true)
        } catch(ex: Exception) {
            assertTrue(ex.message, false)
        }
    }
}