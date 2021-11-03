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

            val result = Connector.select(FAVORITE_TABLE, Pair("user_id", userFavoriteRequest.user_id))
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

    fun addFavorite(favoriteRequest: FavoriteRequest): DomainResponse<Unit> {
        return try {
            Connector.insert(FAVORITE_TABLE, favoriteRequest)
            DomainResponse.Success(Unit)
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }

    fun removeFavorite(favoriteRequest: FavoriteRequest): DomainResponse<Unit> {
        return try {
            Connector.delete(FAVORITE_TABLE, hashMapOf(
                "user_id" to favoriteRequest.user_id,
                "cocktail_id" to favoriteRequest.cocktail_id
            ))
            DomainResponse.Success(Unit)
        } catch (ex: NullPointerException) {
            DomainResponse.Error("Connection Failed")
        }
    }
}