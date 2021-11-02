package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.response.CocktailListResponse
import com.example.bartrend.ui.cocktail.model.CocktailModel
import com.example.bartrend.utils.extensions.toBitmap


fun CocktailListResponse.toModel() = cocktails.map {
        CocktailModel(
            it.id,
            it.name,
            it.description,
            it.image.toBitmap(),
            it.date_added
        )
    }