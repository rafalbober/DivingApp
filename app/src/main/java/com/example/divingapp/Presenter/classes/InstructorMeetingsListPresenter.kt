package com.example.divingapp.Presenter.classes

import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.Iinterfaces.IInstructorMeetingsListPresenter
import com.example.divingapp.Presenter.Iinterfaces.IInstructorProfilePresenter
import com.example.divingapp.Presenter.Iinterfaces.IInstructorUsersListPresenter
import com.example.divingapp.View.IInstructorMeetingsListView
import com.example.divingapp.View.IInstructorProfileView
import com.example.divingapp.View.IInstructorUsersListView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class InstructorMeetingsListPresenter(override val instructorMeetingsListView: IInstructorMeetingsListView) :
        IInstructorMeetingsListPresenter {

}