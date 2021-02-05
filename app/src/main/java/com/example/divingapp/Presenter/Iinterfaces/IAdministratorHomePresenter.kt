package com.example.divingapp.Presenter.Iinterfaces

import com.example.divingapp.View.IAdministratorHomeView


interface IAdministratorHomePresenter {
    val administratorHomeView: IAdministratorHomeView

    fun onLogout()
}