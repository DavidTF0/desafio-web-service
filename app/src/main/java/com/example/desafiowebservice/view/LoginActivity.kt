package com.example.desafiowebservice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafiowebservice.R
import com.example.desafiowebservice.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val btnLogin: Button by lazy { findViewById(R.id.btn_login) }
    private val btnCreateAccount: Button by lazy { findViewById(R.id.btn_create_account) }
    private val fieldLayoutEmail: TextInputLayout by lazy { findViewById(R.id.txt_input_layout_email) }
    private val fieldLayoutPassword: TextInputLayout by lazy { findViewById(R.id.txt_input_layout_password) }
    private val inputEmail: TextInputEditText by lazy { findViewById(R.id.edit_txt_email) }
    private val inputPassword: TextInputEditText by lazy { findViewById(R.id.edit_txt_password) }
    private lateinit var viewModelLogin: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModelLogin = ViewModelProvider(this).get(LoginViewModel::class.java)

        validationOfFields()

        btnLogin.setOnClickListener {
            login()
        }

        btnCreateAccount.setOnClickListener {
            navigateCreateAnAccount()
        }
    }

    private fun validationOfFields(){

        viewModelLogin.fieldEmail.observe(this) { emailValid ->
            if(emailValid){
                fieldLayoutEmail.error = null
            } else {
                fieldLayoutEmail.error = "Obrigatório"
            }

            navigateIfValid()
        }

        viewModelLogin.fieldPassword.observe(this){ passwordValid ->
            if(passwordValid){
                fieldLayoutPassword.error = null
            } else {
                fieldLayoutPassword.error = "Obrigatório"
            }

            navigateIfValid()
        }
    }

    private fun navigateIfValid() {
        if(fieldLayoutEmail.error.isNullOrBlank() && fieldLayoutPassword.error.isNullOrBlank()){
            val intentHomeScreen = Intent(this, HomeScreenActivity::class.java)
            startActivity(intentHomeScreen)
        }
    }

    private fun login(){
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        viewModelLogin.validateEntryFields(email, password)
    }

    private fun navigateCreateAnAccount(){
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }
}