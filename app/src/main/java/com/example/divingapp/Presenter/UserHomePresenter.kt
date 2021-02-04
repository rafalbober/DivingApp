package com.example.divingapp.Presenter

import com.example.divingapp.View.IUserHomeView

class UserHomePresenter(override val userHomeView: IUserHomeView ) : IUserHomePresenter {

    override fun onLogout() {
        userHomeView.logout()
        userHomeView.goToLoginActivity()
    }
}