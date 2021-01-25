package com.example.divingapp.Presenter

import com.example.divingapp.View.IHomeView
import com.example.divingapp.View.ILoginView

interface IHomePresenter {
    val homeView: IHomeView

    fun onLogout()
}