package com.example.divingapp.Presenter

import com.example.divingapp.View.ILoginView

interface ILoginPresenter {
    val loginView: ILoginView

    fun onLogin(email: String, password: String)
}