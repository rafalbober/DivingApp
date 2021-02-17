package com.example.divingapp.activities.userActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.divingapp.Presenter.classes.UserHomePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IUserHomeView
import com.example.divingapp.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserHomeActivity : AppCompatActivity(), IUserHomeView {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        val userHomePresenter = UserHomePresenter(this)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        val btLogout: Button = findViewById(R.id.bt_logout_user)
        val btProfile: Button = findViewById(R.id.bt_user_profile)
        val btInstructor: Button = findViewById(R.id.bt_user_instructor)
        val btMeetings: Button = findViewById(R.id.bt_user_meetings)


        btLogout.setOnClickListener(View.OnClickListener {
            userHomePresenter.onLogout()
        })

        btProfile.setOnClickListener {
            goToProfileActivity()
        }

        btInstructor.setOnClickListener {
            goToInstructorActivity()
        }

        btMeetings.setOnClickListener {
            goToMeetingsActivity()
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

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun goToProfileActivity() {
        startActivity(Intent(this, UserProfileActivity::class.java))
    }

    override fun goToInstructorActivity() {
        val reference = database.getReference("Users").child(firebaseUser.uid).child("InstructorId")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserHomeActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if((!snapshot.exists()) || snapshot.value == "")
                {
                    val intent = Intent(this@UserHomeActivity, UserNoInstructorActivity::class.java)
                    intent.putExtra("userId", firebaseUser.uid)
                    startActivity(intent)
                }
                else
                {
                    val instructorId : String = snapshot.value.toString()
                    val intent = Intent(this@UserHomeActivity, UserInstructorActivity::class.java)
                    intent.putExtra("instructorId", instructorId)
                    startActivity(intent)
                }
            }

        })
        startActivity(Intent(this, UserProfileActivity::class.java))
    }

    override fun goToMeetingsActivity() {
        startActivity(Intent(this, UserProfileActivity::class.java))
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}