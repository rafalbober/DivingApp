package com.example.divingapp.Presenter.classes

import com.example.divingapp.Presenter.Iinterfaces.IInstructorHomePresenter
import com.example.divingapp.View.IInstructorHomeView

class InstructorHomePresenter(override val instructorHomeView: IInstructorHomeView) :
    IInstructorHomePresenter {

    override fun onLogout() {
        instructorHomeView.logout()
        instructorHomeView.goToLoginActivity()
    }

}