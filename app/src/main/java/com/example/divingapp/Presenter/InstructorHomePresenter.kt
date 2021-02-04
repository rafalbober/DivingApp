package com.example.divingapp.Presenter

import com.example.divingapp.View.IInstructorHomeView

class InstructorHomePresenter(override val instructorHomeView: IInstructorHomeView) : IInstructorHomePresenter {

    override fun onLogout() {
        instructorHomeView.logout()
        instructorHomeView.goToLoginActivity()
    }
}