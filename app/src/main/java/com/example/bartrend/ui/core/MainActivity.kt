package com.example.bartrend.ui.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bartrend.R
import com.example.bartrend.utils.extensions.back
import com.example.bartrend.utils.extensions.navigate


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigate(Navigation.LOGIN)
    }

    override fun onBackPressed() {
        if(back()) super.onBackPressed()
    }
}
