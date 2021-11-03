package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.response.CocktailListResponse
import com.example.bartrend.ui.cocktail.model.CocktailModel
import com.example.bartrend.utils.extensions.toBitmap


fun CocktailListResponse.toModel() = cocktails.map {
        CocktailModel(
            id = it.id,
            name = it.name,
            description = it.description,
            image = it.image.toBitmap(),
            dateAdded = it.date_added
        )
    }