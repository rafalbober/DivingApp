package com.example.divingapp.View

interface IRegistrationView {
    fun onRegisterResult(result: String)
    fun goToLoginActivity()
    fun goToHomeActivity()
    fun makeProgressBarVisible()
    fun makeProgressBarInvisible()
}