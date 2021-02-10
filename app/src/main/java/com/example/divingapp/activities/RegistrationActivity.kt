package com.example.divingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.classes.RegistrationPresenter
import com.example.divingapp.R
import com.example.divingapp.View.IRegistrationView
import com.example.divingapp.activities.userActivities.UserHomeActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity(), IRegistrationView {

    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth : FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val etName: EditText = findViewById(R.id.et_name)
        val etSurname: EditText = findViewById(R.id.et_surname)
        val etEmail: EditText = findViewById(R.id.et_registrationEmail)
        val etPhoneNumber: EditText = findViewById(R.id.et_phone)
        val etPassword: EditText = findViewById(R.id.et_registerPassword)
        val etPassword2: EditText = findViewById(R.id.et_registerPassword2)
        val tvLogin: TextView = findViewById(R.id.tv_login)
        val btRegister: Button = findViewById(R.id.bt_register)

        fAuth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar_registration)
        database = FirebaseDatabase.getInstance()

        val registrationPresenter = RegistrationPresenter(this)

        btRegister.setOnClickListener {
            registrationPresenter.onRegister(etName, etSurname, etEmail, etPhoneNumber, etPassword, etPassword2, fAuth)

        }

        tvLogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        })

    }

    override fun onRegisterResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun goToLoginActivity() {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }

    override fun goToHomeActivity() {
        startActivity(Intent(applicationContext, UserHomeActivity::class.java))
    }

    override fun makeProgressBarVisible() {
        progressBar.visibility = View.VISIBLE
    }

    override fun makeProgressBarInvisible() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onSuccessfulRegistration(task: Task<AuthResult>, user: User) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        registerAllUserData(user, firebaseUser.uid)
        val intent: Intent = Intent(this, UserHomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        intent.putExtra("user_id", firebaseUser.uid)
//        intent.putExtra("email", firebaseUser.email)
        startActivity(intent)
        finish()
    }

    private fun registerAllUserData(user: User, userId: String)
    {
        val reference: DatabaseReference = database.getReference("Users")

        reference.child(userId).child("Name").setValue(user.name);
        reference.child(userId).child("Surname").setValue(user.surname);
        reference.child(userId).child("Email").setValue(user.email);
        reference.child(userId).child("PhoneNumber").setValue(user.phoneNumber);
    }


}