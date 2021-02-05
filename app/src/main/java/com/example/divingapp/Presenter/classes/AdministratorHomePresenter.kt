package com.example.divingapp.Presenter.classes

import com.example.divingapp.Presenter.Iinterfaces.IAdministratorHomePresenter
import com.example.divingapp.View.IAdministratorHomeView

class AdministratorHomePresenter(override val administratorHomeView: IAdministratorHomeView) : IAdministratorHomePresenter {

    override fun onLogout() {
        administratorHomeView.logout()
        administratorHomeView.goToLoginActivity()
    }
}