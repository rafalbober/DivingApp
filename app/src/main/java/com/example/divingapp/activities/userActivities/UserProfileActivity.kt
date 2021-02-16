package com.example.divingapp.activities.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.divingapp.Model.User
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorProfileView

class UserProfileActivity : AppCompatActivity(), IInstructorProfileView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
    }

    override fun setEditTextsValues(name: String, surname: String, email: String, phone: String) {
        TODO("Not yet implemented")
    }

    override fun saveDataResult(result: String) {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getSurname(): String {
        TODO("Not yet implemented")
    }

    override fun getEmail(): String {
        TODO("Not yet implemented")
    }

    override fun getPhone(): String {
        TODO("Not yet implemented")
    }

    override fun validUserData(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun editUserData(user: User) {
        TODO("Not yet implemented")
    }
}