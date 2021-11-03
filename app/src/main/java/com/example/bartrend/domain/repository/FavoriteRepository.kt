package com.example.bartrend.domain.repository;

import com.example.bartrend.domain.datasource.FavoriteDataSource;
import com.example.bartrend.domain.model.extensions.toModel
import com.example.bartrend.domain.model.extensions.toRequest
import com.example.bartrend.ui.cocktail.model.FavoriteCocktailModel
import com.example.bartrend.ui.cocktail.model.FavoriteModel
import com.example.bartrend.ui.cocktail.model.FavoriteUserModel
import com.example.bartrend.utils.DomainResponse
import javax.inject.Inject;

class FavoriteRepository @Inject constructor(private val favoriteDataSource: FavoriteDataSource) {

    fun getFavorites(favoriteUserModel: FavoriteUserModel): DomainResponse<List<FavoriteCocktailModel>> {
        return favoriteDataSource.getFavorites(favoriteUserModel.toRequest()).mapSuccess {
            it.toModel()
        }
    }

    fun setFavorite(favoriteModel: FavoriteModel, isFavorite: Boolean): DomainResponse<Unit> {
        return favoriteDataSource.let {
            if(isFavorite) {
                it.addFavorite(favoriteModel.toRequest())
            } else {
                it.removeFavorite(favoriteModel.toRequest())
            }
        }
    }
}