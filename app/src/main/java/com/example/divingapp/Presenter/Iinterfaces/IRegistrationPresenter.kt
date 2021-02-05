package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.IRegistrationView
import com.google.firebase.auth.FirebaseAuth

interface IRegistrationPresenter {
    val registrationView: IRegistrationView

    fun onRegister(name: EditText, surname: EditText, email: EditText, phoneNumber: EditText, password: EditText, password2: EditText, firebaseAuth: FirebaseAuth)
}