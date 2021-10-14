package com.example.bartrend.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.login.model.UserRegisterModel
import com.example.bartrend.utils.ViewModelFactory

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.bind<LoginViewModel, LoginRepository, LoginDataSource>(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = activity?.findViewById<Button>(R.id.login)

        button?.setOnClickListener {
            viewModel.register(UserRegisterModel("a", "b", "c", "d"))
                .observe(viewLifecycleOwner, Observer(::observeRegister))
        }
    }

    private fun observeRegister(state: LoginViewModel.State) {
        when (state) {
            is LoginViewModel.State.Success -> TODO("Implement Success")
            is LoginViewModel.State.Error -> TODO("Implement Error")
        }
    }
}