package com.example.divingapp.Presenter

import com.example.divingapp.View.IUserHomeView

interface IUserHomePresenter {
    val userHomeView: IUserHomeView

    fun onLogout()
}