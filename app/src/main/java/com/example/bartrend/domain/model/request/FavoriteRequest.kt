package com.example.bartrend.domain.model.request

data class FavoriteUserRequest(
    val userId: Int
)

data class FavoriteRequest(
    val userId: Int,
    val cocktailId: Int
)