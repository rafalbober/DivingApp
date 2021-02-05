package com.example.divingapp.Presenter.Iinterfaces

import com.example.divingapp.View.IUserHomeView

interface IUserHomePresenter {
    val userHomeView: IUserHomeView

    fun onLogout()
}