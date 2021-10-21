package com.example.bartrend.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentLoginBinding
import com.example.bartrend.domain.datasource.LoginDataSource
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.core.navigate
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.utils.ViewModelFactory

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.bind<LoginViewModel, LoginRepository, LoginDataSource>(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
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
                requireActivity().navigate(RegisterFragment())
            }
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