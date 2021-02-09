package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divingapp.Presenter.classes.InstructorHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class InstructorHomeActivity : AppCompatActivity(), IInstructorHomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_home)

        val instructorHomePresenter =
            InstructorHomePresenter(this)

        val btProfile: Button = findViewById(R.id.bt_profile_instructor)
        val btUsers: Button = findViewById(R.id.bt_users_instructor)
        val btMeetings: Button = findViewById(R.id.bt_meetings_instructor)
        val btLogout: Button = findViewById(R.id.bt_logout_instructor)
        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email")


        btLogout.setOnClickListener(View.OnClickListener {
            instructorHomePresenter.onLogout()
        })

        btProfile.setOnClickListener(View.OnClickListener {
            instructorHomePresenter.onProfileButtonClick()
        })

        btMeetings.setOnClickListener(View.OnClickListener {
            instructorHomePresenter.onProfileButtonClick()
        })

        btUsers.setOnClickListener(View.OnClickListener {
            instructorHomePresenter.onProfileButtonClick()
        })

    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun goToProfileActivity() {
        startActivity(Intent(this, InstructorProfileActivity::class.java))
    }

    override fun goToUsersListActivity() {
        startActivity(Intent(this, InstructorUsersListActivity::class.java))
    }

    override fun goToMeetingsListActivity() {
        startActivity(Intent(this, InstructorMeetingsListActivity::class.java))
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

}