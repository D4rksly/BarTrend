package com.example.bartrend.domain.repository

import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.model.extensions.toModel
import com.example.bartrend.ui.cocktail.model.CocktailModel
import com.example.bartrend.utils.DomainResponse
import javax.inject.Inject

class CocktailRepository @Inject constructor(private val cocktailDataSource: CocktailDataSource) {

    fun getCocktails(): DomainResponse<List<CocktailModel>> {
        return cocktailDataSource.getCocktails().mapSuccess {
            it.toModel()
        }
    }
}