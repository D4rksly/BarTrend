package com.example.bartrend.ui.core

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bartrend.R
import com.example.bartrend.ui.login.LoginFragment
import com.example.bartrend.utils.Connector
import com.example.bartrend.utils.ViewModelFactory



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(!Connector.connect()) {
            Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT).show()
        }

        val mainFrame = findViewById<FrameLayout>(R.id.mainFrame)

        if(savedInstanceState == null) {
            val ft = supportFragmentManager.beginTransaction()
            val loginFragment = LoginFragment()
            ft.add(mainFrame.id, loginFragment)
            ft.commit()
        }
    }
}
