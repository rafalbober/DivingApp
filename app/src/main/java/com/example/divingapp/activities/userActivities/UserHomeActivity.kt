package com.example.divingapp.activities.userActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divingapp.Presenter.classes.UserHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IUserHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class UserHomeActivity : AppCompatActivity(), IUserHomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        val userHomePresenter = UserHomePresenter(this)

        val btLogout: Button = findViewById(R.id.bt_logout_user)


        btLogout.setOnClickListener(View.OnClickListener {
            userHomePresenter.onLogout()
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