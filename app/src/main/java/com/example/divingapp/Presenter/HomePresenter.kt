package com.example.divingapp.Presenter

import com.example.divingapp.View.IHomeView
import com.google.firebase.auth.FirebaseAuth

class HomePresenter(override val homeView: IHomeView ) : IHomePresenter {

    override fun onLogout() {
        homeView.logout()
        homeView.goToLoginActivity()
    }
}