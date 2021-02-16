package com.example.divingapp.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.divingapp.Presenter.classes.LoginPresenter
import com.example.divingapp.R
import com.example.divingapp.View.ILoginView
import com.example.divingapp.activities.adminActivities.AdministratorHomeActivity
import com.example.divingapp.activities.instructorActivities.InstructorHomeActivity
import com.example.divingapp.activities.userActivities.UserHomeActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity(), ILoginView{

    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var tvReset: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val btLogin: Button = findViewById(R.id.bt_login)
        val tvRegister: TextView = findViewById(R.id.tv_register)
        progressBar = findViewById(R.id.progressBar)
        tvReset = findViewById(R.id.tv_password_reset)

        val loginPresenter = LoginPresenter(this)
        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btLogin.setOnClickListener(View.OnClickListener {
            loginPresenter.onLogin(etEmail,etPassword, fAuth)
        })

        tvRegister.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        })

        tvReset.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Podaj swój adres e-mail.")
            val view: View = layoutInflater.inflate(R.layout.reset_password, null)
            val email = view.findViewById<EditText>(R.id.et_reset)
            builder.setView(view)
            builder.setPositiveButton("Zresetuj hasło.", DialogInterface.OnClickListener { _ , _ ->
                resetPassword(email)
            })
            builder.setNegativeButton("Wróć do logowania.", DialogInterface.OnClickListener { _, _ ->
            })
            builder.show()
        }



    }

    private fun resetPassword(email: EditText)
    {
        if(Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
            fAuth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "E-mail został wysłany.", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onLoginResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    private fun goToUserHomeActivity() {
        val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun goToInstructorHomeActivity() {
        val intent = Intent(this@LoginActivity, InstructorHomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun goToAdministratorHomeActivity() {
        val intent = Intent(this@LoginActivity, AdministratorHomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun makeProgressBarVisible() {
        progressBar.visibility = View.VISIBLE
    }

    override fun makeProgressBarInvisible() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onSuccessfulLogin(task: Task<AuthResult>) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        val usersReference: DatabaseReference = database.getReference("Users").child(firebaseUser.uid)
        val instructorsReference: DatabaseReference = database.getReference("Instructors").child(firebaseUser.uid)
        val administratorsReference: DatabaseReference = database.getReference("Administrators").child(firebaseUser.uid)

        usersReference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Error while retrieving data", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                    goToUserHomeActivity()
            }
        })

        instructorsReference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Error while retrieving data", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    goToInstructorHomeActivity()
                }
            }
        })

        administratorsReference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Error while retrieving data", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                    goToAdministratorHomeActivity()
            }
        })
    }
}