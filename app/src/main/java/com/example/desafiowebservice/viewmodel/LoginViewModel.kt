package com.example.desafiowebservice.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiowebservice.view.HomeScreenActivity

class LoginViewModel : ViewModel() {

    val fieldEmail: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val fieldPassword: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun validateEntryFields(email: String, password: String) {

        when {
            email.isBlank() && password.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(false)
            }
            email.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(true)
            }
            password.isBlank() -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(false)
            }
            else -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(true)
            }
        }
    }


}