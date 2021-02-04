package com.example.divingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Presenter.LoginPresenter
import com.example.divingapp.R
import com.example.divingapp.Utils.UserRole
import com.example.divingapp.View.ILoginView
import com.example.divingapp.activities.instructorActivities.InstructorHomeActivity
import com.example.divingapp.activities.userActivities.UserUserHomeActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity(), ILoginView{

    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth : FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val btLogin: Button = findViewById(R.id.bt_login)
        val tvRegister: TextView = findViewById(R.id.tv_register)
        progressBar = findViewById(R.id.progressBar)

        val loginPresenter = LoginPresenter(this)
        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btLogin.setOnClickListener(View.OnClickListener {
            loginPresenter.onLogin(etEmail,etPassword, fAuth)
        })

        tvRegister.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        })


    }

    override fun onLoginResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun goToHomeActivity() {
        startActivity(Intent(applicationContext, UserUserHomeActivity::class.java))
    }

    override fun makeProgressBarVisible() {
        progressBar.visibility = View.VISIBLE
    }

    override fun makeProgressBarInvisible() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onSuccessfulLogin(task: Task<AuthResult>) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        when (getUserRole(firebaseUser))
        {
            UserRole.Administrator -> Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            UserRole.Instruktor -> {
                val intent = Intent(this, InstructorHomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("user_id", firebaseUser.uid)
                intent.putExtra("email", firebaseUser.email)
                startActivity(intent)
                finish()
            }
            UserRole.Kursant -> {
                intent = Intent(this, UserUserHomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("user_id", firebaseUser.uid)
                intent.putExtra("email", firebaseUser.email)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun getUserRole(firebaseUser: FirebaseUser): UserRole {
        val usersReference: DatabaseReference = database.getReference("Users").child(firebaseUser.uid)
        val instructorsReference: DatabaseReference = database.getReference("Instructors").child(firebaseUser.uid)
        var userRole: UserRole = UserRole.Administrator // wstepnie Admin, potem do zmiany

        usersReference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Error while retrieving data", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                    userRole = UserRole.Kursant
            }
        })

        instructorsReference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Error while retrieving data", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                    userRole = UserRole.Instruktor
            }
        })

        return userRole
    }
}