package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

interface IProfilePresenter {

    val instructorUsersListView: IInstructorProfileView

    fun onStart(firebaseUser: FirebaseUser, database: FirebaseDatabase, userRole: String)
    fun onSave(firebaseUser: FirebaseUser, database: FirebaseDatabase)
}