package com.example.bartrend.utils.extensions

import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bartrend.R

val navigationStack: MutableList<Fragment> = mutableListOf()

/**
 * Navigates to a given screen composed by a fragment
 */
fun FragmentActivity.navigate(fragment: Fragment) {
    navigateAndPersist(fragment::class.java.newInstance())
}

/**
 * Navigates to a given screen composed by a fragment
 * and persists the data inside that fragment
 */
fun FragmentActivity.navigateAndPersist(fragment: Fragment) {
    val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

    val ft = supportFragmentManager.beginTransaction()
    ft.replace(mainFrame.id, fragment)
    ft.commit()

    navigationStack.add(fragment)
}

/**
 * Navigates to a given screen composed by a fragment
 * and sets that screen as the initial screen of the stack
 */
fun FragmentActivity.navigateAndReset(fragment: Fragment) {
    navigationStack.clear()
    navigate(fragment)
}

/**
 * Goes to the previous screen on the navigation stack
 */
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
