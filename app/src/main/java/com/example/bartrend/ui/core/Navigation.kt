package com.example.bartrend.ui.core

import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bartrend.R
import com.example.bartrend.ui.core.Navigation.navigationStack
import com.example.bartrend.ui.login.LoginFragment
import com.example.bartrend.ui.login.RegisterFragment
import kotlin.system.exitProcess

object Navigation {

    //Login
    val LOGIN by lazy { LoginFragment() }
    val REGISTER by lazy { RegisterFragment() }

    val navigationStack : MutableList<Fragment> = mutableListOf()
}

fun FragmentActivity.back(): Boolean {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    navigationStack.removeLast()

    if(navigationStack.isEmpty()) {
        return true
    }

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, navigationStack.last())
    ft.commit()

    return false
}

fun FragmentActivity.navigate(fragment: Fragment) {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, fragment)
    ft.commit()

    navigationStack.add(fragment)
}

fun FragmentActivity.navigateAndClear(fragment: Fragment) {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, fragment::class.java.newInstance())
    ft.commit()

    navigationStack.add(fragment)
}

