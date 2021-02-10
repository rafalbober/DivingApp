package com.example.divingapp.Presenter.Iinterfaces

import android.widget.EditText
import com.example.divingapp.View.IInstructorProfileView

interface IInstructorProfilePresenter {

    val instructorProfileView: IInstructorProfileView

    fun onCreate(userId: String)
}