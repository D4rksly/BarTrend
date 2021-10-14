package com.example.bartrend.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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
            viewModel.register(UserRegisterModel("michael90", "michaelpass", "Michael", "michael90@gmail.com"))
                .observe(viewLifecycleOwner, Observer(::observeRegister))
        }
    }

    private fun observeRegister(state: LoginViewModel.State) {
        when (state) {
            is LoginViewModel.State.Success -> Toast.makeText(context, "Welcome! ${state.userModel.name}", Toast.LENGTH_SHORT).show()
            is LoginViewModel.State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is LoginViewModel.State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}