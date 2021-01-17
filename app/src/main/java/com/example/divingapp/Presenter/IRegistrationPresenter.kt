package com.example.divingapp.Presenter

import android.widget.EditText
import com.example.divingapp.View.IRegistrationView

interface IRegistrationPresenter {
    val registrationView: IRegistrationView

    fun onRegister(name: EditText, surname: EditText, email: EditText, phoneNumber: EditText, password: EditText, password2: EditText)
}