package com.example.divingapp.Presenter.classes

import android.widget.EditText
import com.example.divingapp.Presenter.Iinterfaces.IInstructorHomePresenter
import com.example.divingapp.Presenter.Iinterfaces.IInstructorProfilePresenter
import com.example.divingapp.View.IInstructorHomeView
import com.example.divingapp.View.IInstructorProfileView

class InstructorProfilePresenter(override val instructorProfileView: IInstructorProfileView) :
        IInstructorProfilePresenter {

    override fun onCreate(userId: String) {
        instructorProfileView.setEditTextsValues("sth", "sth", "sth", "sth")
    }

}