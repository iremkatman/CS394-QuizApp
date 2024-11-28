package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = MutableLiveData<Boolean>()

    fun login() {
        // Örnek kontrol
        if (!nickname.value.isNullOrEmpty() && nickname.value == "test" && password.value == "1234") {
            loginSuccess.value = true
        } else {
            loginSuccess.value = false
        }
    }
}
