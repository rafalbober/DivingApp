package com.example.divingapp.Presenter

import com.example.divingapp.View.IRegistrationView

interface IRegistrationPresenter {
    val registrationView: IRegistrationView

    fun onRegister(name: String, surname: String, email: String, phoneNumber: String, password: String, password2: String)
}