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
import com.example.bartrend.ui.core.Navigation
import com.example.bartrend.ui.core.back
import com.example.bartrend.ui.core.navigate
import com.example.bartrend.ui.login.model.UserRegisterModel
import com.example.bartrend.utils.ViewModelFactory

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

        with(binding) {
            register.setOnClickListener { register() }

            cancel.setOnClickListener {
                requireActivity().back()
            }
        }
    }

    fun register() {
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