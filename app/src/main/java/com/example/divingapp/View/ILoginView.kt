package com.example.divingapp.View

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface ILoginView {
    fun onLoginResult(result: String)
    fun goToHomeActivity()
    fun makeProgressBarVisible()
    fun makeProgressBarInvisible()
    fun onSuccessfulLogin(task: Task<AuthResult>)
}