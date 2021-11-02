package com.example.bartrend.domain.datasource

import com.example.bartrend.domain.model.request.FavoriteRequest
import com.example.bartrend.domain.model.request.FavoriteUserRequest
import com.example.bartrend.domain.model.response.FavoriteCocktailResponse
import com.example.bartrend.utils.Connector
import com.example.bartrend.utils.DomainResponse
import java.lang.NullPointerException
import javax.inject.Inject

class FavoriteDataSource @Inject constructor() {

    companion object {
        private const val FAVORITE_TABLE = "Favorites"
    }

    fun getFavorites(userFavoriteRequest: FavoriteUserRequest): DomainResponse<FavoriteCocktailResponse> {
        return try {

            val result = Connector.select(FAVORITE_TABLE, Pair("user_id", userFavoriteRequest.userId))
            val response = mutableListOf<Int>()

            while(result.next()) {
                response.add(result.getInt("cocktail_id"))
            }

            DomainResponse.Success(
                FavoriteCocktailResponse(response)
            )
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }

    fun setFavorite(favoriteRequest: FavoriteRequest): DomainResponse<Unit> {
        return try {
            Connector.insert(FAVORITE_TABLE, favoriteRequest)
            DomainResponse.Success(Unit)
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}