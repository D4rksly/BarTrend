package com.example.bartrend.ui.core

import com.example.bartrend.ui.cocktail.view.CocktailListFragment
import com.example.bartrend.ui.login.view.LoginFragment
import com.example.bartrend.ui.login.view.RegisterFragment

object Navigation {
    //Login
    val LOGIN by lazy { LoginFragment() }
    val REGISTER by lazy { RegisterFragment() }

    //Home
    val HOME by lazy { CocktailListFragment() }
}