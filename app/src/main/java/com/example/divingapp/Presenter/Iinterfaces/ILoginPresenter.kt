package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.ILoginView
import com.google.firebase.auth.FirebaseAuth

interface ILoginPresenter {
    val loginView: ILoginView

    fun onLogin(email: EditText, password: EditText, firebaseAuth: FirebaseAuth)
}