package com.example.divingapp.View

import com.example.divingapp.Model.User

interface IInstructorProfileView {

    fun setEditTextsValues(name: String, surname: String, email: String, phone: String)
    fun saveDataResult(result: String)
    fun getName() : String
    fun getSurname() : String
    fun getEmail() : String
    fun getPhone() : String
    fun validUserData(user: User) : Boolean
    fun editUserData(user: User)
}