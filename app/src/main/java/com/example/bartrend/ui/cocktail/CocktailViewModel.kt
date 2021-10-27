package com.example.bartrend.ui.cocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.ui.cocktail.model.CocktailModel
import kotlinx.coroutines.launch

class CocktailViewModel(private val cocktailRepository: CocktailRepository) : ViewModel() {

    sealed class State {
        data class Success(val cocktails: List<CocktailModel>): State()
        data class Error(val error: String): State()
        object Loading: State()
    }

    fun getCocktails(): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)

        viewModelScope.launch {
            cocktailRepository.getCocktails()
                .success {
                     result.value = State.Success(it)
                }.failure {
                    result.value = State.Error(it)
                }
        }

        return result
    }
}