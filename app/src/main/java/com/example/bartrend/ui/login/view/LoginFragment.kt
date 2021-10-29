package com.example.bartrend.ui.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentLoginBinding
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.core.Navigation
import com.example.bartrend.ui.login.LoginViewModel
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.extensions.navigate
import com.example.bartrend.utils.extensions.navigateAndReset
import com.example.bartrend.utils.extensions.viewBinding

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel
    private val binding by viewBinding<FragmentLoginBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.bind<LoginViewModel, LoginRepository, LoginDataSource>(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            login.setOnClickListener {
                viewModel.login(
                    UserLoginModel(
                        email.text.toString(),
                        password.text.toString()
                    )
                )
                    .observe(viewLifecycleOwner, Observer(::observeLogin))
            }

            register.setOnClickListener {
                requireActivity().navigate(Navigation.REGISTER)
            }
        }
    }

    private fun observeLogin(state: LoginViewModel.State) {
        when (state) {
            is LoginViewModel.State.Success -> requireActivity().navigateAndReset(Navigation.HOME)
            is LoginViewModel.State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is LoginViewModel.State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}