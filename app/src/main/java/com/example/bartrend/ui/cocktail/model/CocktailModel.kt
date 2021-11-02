package com.example.bartrend.ui.cocktail.model

import android.graphics.Bitmap
import java.util.*

data class CocktailModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: Bitmap,
    val dateAdded: Date
)