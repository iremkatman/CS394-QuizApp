package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login() {
        val emailValue = email.value
        val passwordValue = password.value

        if (!emailValue.isNullOrEmpty() && !passwordValue.isNullOrEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(emailValue, passwordValue)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loginSuccess.value = true
                    } else {
                        loginSuccess.value = false
                        loginError.value = task.exception?.message ?: "Unknown error occurred"
                    }
                }
        } else {
            loginError.value = "Email or password cannot be empty"
        }
    }
}
