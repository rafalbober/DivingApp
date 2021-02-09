package com.example.divingapp.Presenter.Iinterfaces

import com.example.divingapp.View.IInstructorHomeView

interface IInstructorHomePresenter {

    val instructorHomeView: IInstructorHomeView

    fun onLogout()
    fun onProfileButtonClick()

}