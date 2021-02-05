package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divingapp.Presenter.InstructorHomePresenter
import com.example.divingapp.Presenter.UserHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class InstructorHomeActivity : AppCompatActivity(), IInstructorHomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_home)

        val instructorHomePresenter = InstructorHomePresenter(this)

        val btLogout: Button = findViewById(R.id.bt_logout_instructor)
        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email")


        btLogout.setOnClickListener(View.OnClickListener {
            instructorHomePresenter.onLogout()
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