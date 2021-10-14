package com.example.bartrend.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    sealed class State {
        data class Success(val userModel: UserModel): State()
        data class Error(val error: String): State()
        object Loading: State()
    }

    fun register(registerModel: UserRegisterModel): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)
        loginRepository.register(registerModel)
            .success {
                result.value = State.Success(it)
            }.failure {
                result.value = State.Error(it)
            }
        return result
    }
}