package com.example.bartrend.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bartrend.domain.repository.LoginRepository
import com.example.bartrend.ui.login.model.UserLoginModel
import com.example.bartrend.ui.login.model.UserModel
import com.example.bartrend.ui.login.model.UserRegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    sealed class State {
        data class Success(val userModel: UserModel): State()
        data class Error(val error: String): State()
        object Loading: State()
    }

    fun register(registerModel: UserRegisterModel): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.checkEmailAvailability(registerModel.email)
                .success {
                    loginRepository.register(registerModel)
                        .success { result.postValue(State.Success(it)) }
                        .failure { result.postValue(State.Error(it)) }
                }.failure {  result.postValue(State.Error(it)) }
        }

        return result
    }

    fun login(loginModel: UserLoginModel): LiveData<State> {
        val result: MutableLiveData<State> = MutableLiveData(State.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.login(loginModel)
                .success {
                    result.postValue(State.Success(it))
                }.failure {
                    result.postValue(State.Error(it))
                }
        }
        return result
    }
}