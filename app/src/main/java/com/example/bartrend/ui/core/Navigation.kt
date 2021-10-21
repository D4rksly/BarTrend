package com.example.bartrend.ui.core

import com.example.bartrend.ui.login.LoginFragment
import com.example.bartrend.ui.login.RegisterFragment

object Navigation {
    //Login
    val LOGIN by lazy { LoginFragment() }
    val REGISTER by lazy { RegisterFragment() }
}