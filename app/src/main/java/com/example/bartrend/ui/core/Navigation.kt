package com.example.bartrend.ui.core

import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.ui.cocktail.view.CocktailListFragment
import com.example.bartrend.ui.login.LoginViewModel
import com.example.bartrend.ui.login.view.LoginFragment
import com.example.bartrend.ui.login.view.RegisterFragment
import com.example.bartrend.utils.ViewModelContent
import com.example.bartrend.utils.bindViewModel

object Navigation {
    //Login
    val LOGIN by lazy { LoginFragment() }
    val REGISTER by lazy { RegisterFragment() }

    //Home
    val HOME by lazy { CocktailListFragment() }
}

val viewModels: List<ViewModelContent> = listOf(
    bindViewModel<LoginViewModel, LoginRepository, LoginDataSource>(),
    bindViewModel<CocktailViewModel, CocktailRepository, CocktailDataSource>()
)