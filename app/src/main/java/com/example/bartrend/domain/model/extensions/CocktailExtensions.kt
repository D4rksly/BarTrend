package com.example.bartrend.domain.model.extensions

import com.example.bartrend.domain.model.response.CocktailListResponse
import com.example.bartrend.ui.login.model.CocktailModel


fun CocktailListResponse.toModel() = cocktails.map {
        CocktailModel(
            it.name,
            it.description
        )
    }