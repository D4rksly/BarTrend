package com.example.bartrend.ui.cocktail.model

import com.example.bartrend.ui.login.model.UserModel

data class FavoriteUserModel(
    val user: UserModel
)

data class FavoriteModel(
    val user: UserModel,
    val cocktail: CocktailModel
)

data class FavoriteCocktailModel(
    val cocktailId: Int
)