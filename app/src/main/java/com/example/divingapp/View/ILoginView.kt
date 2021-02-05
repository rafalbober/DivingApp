package com.example.divingapp.View

import com.example.divingapp.Utils.UserRole
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface ILoginView {
    fun onLoginResult(result: String)
    fun makeProgressBarVisible()
    fun makeProgressBarInvisible()
    fun onSuccessfulLogin(task: Task<AuthResult>)
}