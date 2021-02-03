package com.example.divingapp.Presenter

import android.widget.EditText
import com.example.divingapp.Model.User
import com.example.divingapp.View.IRegistrationView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegistrationPresenter(override val registrationView: IRegistrationView) : IRegistrationPresenter {

    override fun onRegister(
        name: EditText,
        surname: EditText,
        email: EditText,
        phoneNumber: EditText,
        password: EditText,
        password2: EditText,
        firebaseAuth: FirebaseAuth
    ) {
        val user = User(
                name = name.text.toString().trim(),
                surname = surname.text.toString().trim(),
                email = email.text.toString().trim(),
                phoneNumber = phoneNumber.text.toString().trim(),
                password = password.text.toString().trim(),
                password2 = password2.text.toString().trim()
        )
        val registrationResult = user.isRegistrationDataValid()
        registrationView.makeProgressBarVisible()

        if(registrationResult) {
            firebaseAuth.createUserWithEmailAndPassword((user.email) as String, (user.password) as String).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            registrationView.makeProgressBarInvisible()
                            registrationView.onRegisterResult("Registration succeeded.")
                            registrationView.onSuccessfulRegistration(task, user)
                        } else {
                            registrationView.makeProgressBarInvisible()
                            registrationView.onRegisterResult(task.exception!!.message.toString())
                        }
                    }
            )
        }
        else {
            registrationView.makeProgressBarInvisible()
            registrationView.onRegisterResult("Registration failed")

            if(!user.isNameValid())
                name.error = "Name is not valid"
            if(!user.isSurnameValid())
                surname.error = "Surname is not valid"
            if(!user.isEmailValid())
                email.error = "Email is not valid"
            if(!user.isPhoneNumberValid())
                phoneNumber.error = "Phone number is not valid"
            if(!user.isPasswordValid())
                password.error = "Password is not valid"
            if(!user.arePasswordsMatches())
                password2.error = "Passwords are not the same"
        }

    }
}