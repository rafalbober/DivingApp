package com.example.divingapp.Presenter

import com.example.divingapp.View.IInstructorHomeView

interface IInstructorHomePresenter {

    val instructorHomeView: IInstructorHomeView

    fun onLogout()
}