package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.response.CocktailListResponse
import com.example.bartrend.domain.model.response.CocktailResponse
import com.example.bartrend.utils.Connector
import com.example.bartrend.utils.DomainResponse
import java.lang.NullPointerException
import javax.inject.Inject

class CocktailDataSource @Inject constructor(){
    companion object {
        private const val COCKTAILS_TABLE = "Cocktails"
    }

    fun getCocktails(): DomainResponse<CocktailListResponse> {
        return try {

            val result = Connector.select(COCKTAILS_TABLE)
            val response = mutableListOf<CocktailResponse>()

            while(result.next()) {
                response.add(
                    CocktailResponse(
                        result.getString("name"),
                        result.getString("description"),
                        result.getBlob("image"),
                        result.getDate("date_added")
                    )
                )
            }

            DomainResponse.Success(
                CocktailListResponse(
                    response
                )
            )
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}