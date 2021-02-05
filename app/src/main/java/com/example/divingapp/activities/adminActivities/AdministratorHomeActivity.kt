package com.example.divingapp.activities.adminActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divingapp.Presenter.classes.AdministratorHomePresenter
import com.example.divingapp.Presenter.classes.UserHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IAdministratorHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class AdministratorHomeActivity : AppCompatActivity(), IAdministratorHomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator_home)

        val administratorHomePresenter = AdministratorHomePresenter(this)

        val btLogout: Button = findViewById(R.id.bt_logout_administrator)
        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email")


        btLogout.setOnClickListener(View.OnClickListener {
            administratorHomePresenter.onLogout()
        })

    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}
