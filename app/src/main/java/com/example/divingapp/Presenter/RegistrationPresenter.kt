package com.example.divingapp.Presenter

import com.example.divingapp.Model.User
import com.example.divingapp.View.IRegistrationView

class RegistrationPresenter(override val registrationView: IRegistrationView) : IRegistrationPresenter {

    override fun onRegister(
        name: String,
        surname: String,
        email: String,
        phoneNumber: String,
        password: String,
        password2: String
    ) {
        val user = User(name = name, surname = surname, email = email, phoneNumber = phoneNumber, password = password, password2 = password2)
        val registrationResult = user.isRegistrationDataValid()

        if(registrationResult) {
            registrationView.onRegisterResult("Registration succeeded")
            registrationView.goToLoginActivity()
        }
        else
            registrationView.onRegisterResult("Registration failed")
    }
}