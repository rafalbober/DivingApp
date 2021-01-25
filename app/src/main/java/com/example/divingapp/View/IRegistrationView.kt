package com.example.divingapp.View

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface IRegistrationView {
    fun onRegisterResult(result: String)
    fun goToLoginActivity()
    fun goToHomeActivity()
    fun makeProgressBarVisible()
    fun makeProgressBarInvisible()
    fun onSuccessfulRegistration(task: Task<AuthResult>)
}