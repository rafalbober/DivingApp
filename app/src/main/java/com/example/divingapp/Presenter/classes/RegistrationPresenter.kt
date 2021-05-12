package com.example.divingapp.Presenter.classes

import android.widget.EditText
import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.Iinterfaces.IRegistrationPresenter
import com.example.divingapp.View.IRegistrationView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegistrationPresenter(override val registrationView: IRegistrationView) :
    IRegistrationPresenter {

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
        //registrationView.makeProgressBarVisible()

        if(registrationResult) {
            firebaseAuth.createUserWithEmailAndPassword((user.email) as String, (user.password) as String).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            registrationView.makeProgressBarInvisible()
                            registrationView.onRegisterResult("Rejestracja powiodła się.")
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
            registrationView.onRegisterResult("Rejestracja nie powiodła się")

            if(!user.isNameValid())
                name.error = "Imię jest niepoprawne"
            if(!user.isSurnameValid())
                surname.error = "Nazwisko jest niepoprawne"
            if(!user.isEmailValid())
                email.error = "E-mail jest niepoprawny"
            if(!user.isPhoneNumberValid())
                phoneNumber.error = "Numer telefonu jest niepoprawny"
            if(!user.isPasswordValid())
                password.error = "Hasło nie spełnia wymagań"
            if(!user.arePasswordsMatches())
                password2.error = "Hasła nie są takie same."
        }

    }
}