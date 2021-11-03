package com.example.bartrend.ui.cocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.domain.repository.FavoriteRepository
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.cocktail.model.CocktailModel
import com.example.bartrend.ui.cocktail.model.FavoriteCocktailModel
import com.example.bartrend.ui.cocktail.model.FavoriteModel
import com.example.bartrend.ui.cocktail.model.FavoriteUserModel
import com.example.bartrend.ui.login.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CocktailViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val cocktailRepository: CocktailRepository,
    private val loginRepository: LoginRepository)
    : ViewModel() {

    sealed class State {
        data class CocktailSuccess(val cocktails: List<CocktailModel>): State()
        data class FavoriteSuccess(val favorites: List<FavoriteCocktailModel>): State()
        object SetFavoriteSuccess: State()
        
        data class Error(val error: String): State()
        object Loading: State()
    }

    fun getCocktails(): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            cocktailRepository.getCocktails()
                .success {
                    result.postValue(State.CocktailSuccess(it))
                }.failure {
                    result.postValue(State.Error(it))
                }
        }

        return result
    }

    fun getFavorites(): LiveData<State>  {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)
        val user: UserModel = loginRepository.getUser()

        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.getFavorites(FavoriteUserModel(user)).
                success {
                    result.postValue(State.FavoriteSuccess(it))
                }.failure {
                    result.postValue(State.Error(it))
                }
        }

        return result
    }

    fun setFavorite(cocktailId: Int): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)
        val user: UserModel = loginRepository.getUser()

        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.setFavorite(FavoriteModel(user, cocktailId))
                .success {
                    result.postValue(State.SetFavoriteSuccess)
                }.failure {
                    result.postValue(State.Error(it))
                }
        }

        return result
    }
}