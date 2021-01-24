package com.example.divingapp.View

interface ILoginView {
    fun onLoginResult(result: String)
    fun goToHomeActivity()
    fun makeProgressBarVisible()
    fun makeProgressBarInvisible()
}