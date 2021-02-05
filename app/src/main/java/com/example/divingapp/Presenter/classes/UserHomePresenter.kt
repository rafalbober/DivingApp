package com.example.divingapp.Presenter.classes

import com.example.divingapp.Presenter.Iinterfaces.IUserHomePresenter
import com.example.divingapp.View.IUserHomeView

class UserHomePresenter(override val userHomeView: IUserHomeView ) :
    IUserHomePresenter {

    override fun onLogout() {
        userHomeView.logout()
        userHomeView.goToLoginActivity()
    }
}