package com.example.bartrend.ui.core

import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bartrend.R
import com.example.bartrend.ui.login.LoginFragment
import com.example.bartrend.utils.Connector
import com.example.bartrend.utils.ViewModelFactory



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigate(Navigation.LOGIN)
    }
}
