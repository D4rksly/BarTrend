package com.example.bartrend.domain.model.request

data class FavoriteUserRequest(
    val user_id: Int
)

data class FavoriteRequest(
    val user_id: Int,
    val cocktail_id: Int
)