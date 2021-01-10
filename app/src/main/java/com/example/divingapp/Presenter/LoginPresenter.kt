package com.example.divingapp.Presenter

import com.example.divingapp.Model.User
import com.example.divingapp.View.ILoginView

class LoginPresenter(override val loginView: ILoginView) : ILoginPresenter {

    override fun onLogin(email: String, password: String) {
        val user = User(email = email, password = password)
        val loginResult = user.isValidLoginData()

        if(loginResult)
            loginView.onLoginResult("good login data")
        else
            loginView.onLoginResult("bad login data")
    }

}