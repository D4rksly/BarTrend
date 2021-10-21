package com.example.bartrend.ui.core

import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bartrend.R
import com.example.bartrend.ui.login.LoginFragment
import com.example.bartrend.ui.login.RegisterFragment

object Navigation {
    //Login
    val LOGIN by lazy { LoginFragment() }
    val REGISTER by lazy { RegisterFragment() }
}

fun FragmentActivity.navigate(fragment: Fragment) {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, fragment)
    ft.commit()
}

fun FragmentActivity.navigateAndClear(fragment: Fragment) {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, fragment::class.java.newInstance())
    ft.commit()
}

