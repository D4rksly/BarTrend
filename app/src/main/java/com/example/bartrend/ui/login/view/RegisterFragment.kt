package com.example.bartrend.ui.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentRegisterBinding
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.core.MainActivity
import com.example.bartrend.ui.core.Navigation
import com.example.bartrend.ui.login.LoginViewModel
import com.example.bartrend.ui.login.model.UserRegisterModel
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.extensions.*
import javax.inject.Inject

class RegisterFragment: Fragment(R.layout.fragment_register) {

    @Inject  internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding<FragmentRegisterBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).provideMainComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            register.setOnClickListener {
                if(areFieldsValid()) {
                    register()
                }
            }

            cancel.setOnClickListener {
                requireActivity().back()
            }
        }
    }

    private fun areFieldsValid(): Boolean {
        with(binding) {
            if(name.text.isEmpty()) {
                Toast.makeText(context, "Name Required", Toast.LENGTH_SHORT).show()
                return false
            }

            if(email.text.isEmpty()) {
                Toast.makeText(context, "Email Required", Toast.LENGTH_SHORT).show()
                return false
            }

            if(password.text.isEmpty()) {
                Toast.makeText(context, "Password Required", Toast.LENGTH_SHORT).show()
                return false
            }

            if(!email.text.toString().isEmailValid()) {
                Toast.makeText(context, "Email Invalid", Toast.LENGTH_SHORT).show()
                return false
            }

            if(password.text.toString() != passwordConfirm.text.toString()) {
                Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun register() {
        with(binding) {
            viewModel.register(
                UserRegisterModel(
                    email.text.toString(),
                    name.text.toString(),
                    password.text.toString()
                )
            )
                .observe(viewLifecycleOwner, Observer(::observeLogin))
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