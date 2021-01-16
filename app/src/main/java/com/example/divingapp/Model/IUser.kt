package com.example.divingapp.Model

import com.example.divingapp.Utils.UserRole

interface IUser {
    val name: String?
    val surname: String?
    val email: String?
    val phoneNumber: String?
    val userRole: UserRole?
    val password: String?
    val password2: String?
    val mappedPassword: String?

    fun isEmailValid() : Boolean
    fun isPasswordValid() : Boolean
    fun isNameValid() : Boolean
    fun isSurnameValid() : Boolean
    fun isPhoneNumberValid() : Boolean
    fun isLoginDataValid() : Boolean
    fun arePasswordsMatches(): Boolean
    fun isRegistrationDataValid(): Boolean
}