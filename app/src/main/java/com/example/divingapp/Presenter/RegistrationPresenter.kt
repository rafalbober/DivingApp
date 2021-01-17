package com.example.divingapp.Presenter

import android.widget.EditText
import com.example.divingapp.Model.User
import com.example.divingapp.View.IRegistrationView

class RegistrationPresenter(override val registrationView: IRegistrationView) : IRegistrationPresenter {

    override fun onRegister(
        name: EditText,
        surname: EditText,
        email: EditText,
        phoneNumber: EditText,
        password: EditText,
        password2: EditText
    ) {
        val user = User(name = name.text.toString(), surname = surname.text.toString(), email = email.text.toString(), phoneNumber = phoneNumber.text.toString(), password = password.text.toString(), password2 = password2.text.toString())
        val registrationResult = user.isRegistrationDataValid()

        if(registrationResult) {
            registrationView.onRegisterResult("Registration succeeded")
            registrationView.goToLoginActivity()
        }
        else {
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