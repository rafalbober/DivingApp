package com.example.divingapp.Model

import android.util.Patterns
import com.example.divingapp.Utils.UserRole

class User(override val name: String? = null, override val surname: String? = null, override val email: String? = null, override val phoneNumber: String? = null, override val userRole: UserRole? = null, override val mappedPassword: String? = null, override val password: String? = null,
           override val password2: String? = null
) : IUser{

    override fun isEmailValid(): Boolean {
        return !(email.isNullOrBlank()) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun isPasswordValid(): Boolean {
        return !(password.isNullOrEmpty()) && password.length < 20 && password.length > 3
    }

    override fun isNameValid(): Boolean {
        return !(name.isNullOrBlank()) && name.length < 50
    }

    override fun isSurnameValid(): Boolean {
        return !(surname.isNullOrBlank()) && surname.length < 50
    }

    override fun isPhoneNumberValid(): Boolean {
        return !(name.isNullOrBlank()) && name.length < 20 && Patterns.PHONE.matcher(phoneNumber).matches()
    }

    override fun isLoginDataValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    override fun arePasswordsMatches(): Boolean {
        return password.equals(password2)
    }

    override fun isRegistrationDataValid(): Boolean {
        return isNameValid() && isSurnameValid() && isEmailValid() && isPhoneNumberValid() && isPasswordValid() && arePasswordsMatches()
    }


}