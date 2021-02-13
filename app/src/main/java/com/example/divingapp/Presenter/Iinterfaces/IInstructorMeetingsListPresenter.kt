package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.IInstructorMeetingsListView
import com.example.divingapp.View.IInstructorProfileView
import com.example.divingapp.View.IInstructorUsersListView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

interface IInstructorMeetingsListPresenter {

    val instructorMeetingsListView: IInstructorMeetingsListView
}