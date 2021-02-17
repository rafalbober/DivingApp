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
import com.google.firebase.auth.FirebaseUser

class AdministratorHomeActivity : AppCompatActivity(), IAdministratorHomeView {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator_home)

        auth = FirebaseAuth.getInstance()

        val administratorHomePresenter = AdministratorHomePresenter(this)

        val btLogout: Button = findViewById(R.id.bt_logout_administrator)
        val btProfile: Button = findViewById(R.id.bt_administrator_profile)
        val btCreateInstructor: Button = findViewById(R.id.bt_create_instructor)
        val btInstructorList: Button = findViewById(R.id.bt_administrator_instructors_list)

        btLogout.setOnClickListener(View.OnClickListener {
            administratorHomePresenter.onLogout()
        })

        btProfile.setOnClickListener(View.OnClickListener {
            goToProfileActivity()
        })

        btCreateInstructor.setOnClickListener(View.OnClickListener {
            goToCreateInstructorActivity()
        })

        btInstructorList.setOnClickListener(View.OnClickListener {
            goToInstructorsListActivity()
        })

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

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun goToProfileActivity() {
        startActivity(Intent(this, AdministratorProfileActivity::class.java))
    }

    override fun goToInstructorsListActivity() {
        startActivity(Intent(this, AdministratorInstructorsListActivity::class.java))
    }

    override fun goToCreateInstructorActivity() {
        startActivity(Intent(this, RegisterInstructorActivity::class.java))
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}
