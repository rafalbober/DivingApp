package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

interface IInstructorProfilePresenter {

    val instructorProfileView: IInstructorProfileView

    fun onStart(firebaseUser: FirebaseUser, database: FirebaseDatabase)
    fun onSave(firebaseUser: FirebaseUser, database: FirebaseDatabase)
}