package com.example.divingapp.Presenter

import com.example.divingapp.Model.User
import com.example.divingapp.View.ILoginView

class LoginPresenter(override val loginView: ILoginView) : ILoginPresenter {

    override fun onLogin(email: String, password: String) {
        val user = User(email = email, password = password)
        val loginResult = user.isLoginDataValid()

        if(loginResult) {
            loginView.onLoginResult("good login data")
            loginView.goToHomeLayout()
        }
        else
            loginView.onLoginResult("bad login data")
    }

}