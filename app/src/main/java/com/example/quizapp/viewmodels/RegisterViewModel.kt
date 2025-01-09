package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    val nickname = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()


}
