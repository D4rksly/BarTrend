package com.example.bartrend.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentRegisterBinding
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.login.model.UserRegisterModel
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.extensions.back
import com.example.bartrend.utils.extensions.isEmailValid
import java.lang.Exception

class RegisterFragment: Fragment(R.layout.fragment_register) {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.bind<LoginViewModel, LoginRepository, LoginDataSource>(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
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
            is LoginViewModel.State.Success -> Toast.makeText(context, "Welcome! ${state.userModel.name}", Toast.LENGTH_SHORT).show()
            is LoginViewModel.State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is LoginViewModel.State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}