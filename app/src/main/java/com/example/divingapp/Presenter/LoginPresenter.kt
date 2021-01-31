package com.example.divingapp.Presenter

import android.widget.EditText
import com.example.divingapp.Model.User
import com.example.divingapp.View.ILoginView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(override val loginView: ILoginView) : ILoginPresenter {

    override fun onLogin(email: EditText, password: EditText, firebaseAuth: FirebaseAuth) {
        val user = User(email = email.text.toString().trim(), password = password.text.toString().trim())
        val loginResult = user.isLoginDataValid()

        loginView.makeProgressBarVisible()

        if(loginResult) {
            firebaseAuth.signInWithEmailAndPassword((user.email) as String, (user.password) as String).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            loginView.makeProgressBarInvisible()
                            loginView.onLoginResult("login succeeded.")
                            loginView.onSuccessfulLogin(task)
                        } else {
                            loginView.makeProgressBarInvisible()
                            loginView.onLoginResult(task.exception!!.message.toString())
                        }
            })
        }
        else {
                if (!user.isEmailValid())
                    email.error = "Email is not valid"
                if (!user.isPasswordValid())
                    password.error = "Password is not valid"
            }
    }
}