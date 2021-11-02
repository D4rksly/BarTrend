package com.example.bartrend.domain.model.response

import java.sql.Blob
import java.util.*

data class CocktailResponse(
    val id: Int,
    val name: String,
    val description: String,
    val image: Blob,
    val date_added: Date
)

data class CocktailListResponse(
    val cocktails: List<CocktailResponse>
)