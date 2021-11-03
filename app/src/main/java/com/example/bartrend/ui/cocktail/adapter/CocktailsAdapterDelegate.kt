package com.example.bartrend.ui.cocktail.adapter

interface CocktailsAdapterDelegate {
    fun onToggleFavorite(isFavorite: Boolean, cocktailId: Int)
}