package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.divingapp.Presenter.classes.InstructorHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InstructorHomeActivity : AppCompatActivity(), IInstructorHomeView {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_home)

        val instructorHomePresenter = InstructorHomePresenter(this)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btProfile: Button = findViewById(R.id.bt_profile_instructor)
        val btUsers: Button = findViewById(R.id.bt_users_instructor)
        val btMeetings: Button = findViewById(R.id.bt_meetings_instructor)
        val btLogout: Button = findViewById(R.id.bt_logout_instructor)
        val btAddUser: Button = findViewById(R.id.bt_instructor_add_user)

        btLogout.setOnClickListener {
            instructorHomePresenter.onLogout()
        }

        btProfile.setOnClickListener {
            goToProfileActivity()
        }

        btMeetings.setOnClickListener {
            goToMeetingsListActivity()
        }

        btUsers.setOnClickListener {
            goToUsersListActivity()
        }

        btAddUser.setOnClickListener {
            goToAddUserActivity()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?) {
        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


    private fun goToAddUserActivity() {
        startActivity(Intent(this, InstructorAddUserActivity::class.java))
    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun goToProfileActivity() {
        val intent = Intent(this, InstructorProfileActivity::class.java)
        startActivity(intent)
    }

    private fun goToUsersListActivity() {
        val intent = Intent(this, InstructorUsersListActivity::class.java)
        startActivity(intent)
    }

    private fun goToMeetingsListActivity() {
        val intent = Intent(this, InstructorMeetingsListActivity::class.java)
        startActivity(intent)
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

}