package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.request.FavoriteRequest
import com.example.bartrend.domain.model.request.FavoriteUserRequest
import com.example.bartrend.domain.model.response.FavoriteCocktailResponse
import com.example.bartrend.ui.cocktail.model.FavoriteCocktailModel
import com.example.bartrend.ui.cocktail.model.FavoriteModel
import com.example.bartrend.ui.cocktail.model.FavoriteUserModel

fun FavoriteUserModel.toRequest() = FavoriteUserRequest(
    user_id = user.id
)

fun FavoriteModel.toRequest() = FavoriteRequest(
    user_id = user.id,
    cocktail_id = cocktailId
)

fun FavoriteCocktailResponse.toModel() =  cocktails.map {
    FavoriteCocktailModel(
        cocktailId = it
    )
}


