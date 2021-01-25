package com.example.divingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Presenter.RegistrationPresenter
import com.example.divingapp.View.IRegistrationView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity(), IRegistrationView {

    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth : FirebaseAuth

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
        startActivity(Intent(applicationContext, HomeActivity::class.java))
    }

    override fun makeProgressBarVisible() {
        progressBar.visibility = View.VISIBLE
    }

    override fun makeProgressBarInvisible() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onSuccessfulRegistration(task: Task<AuthResult>) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        val intent: Intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("user_id", firebaseUser.uid)
        intent.putExtra("email", firebaseUser.email)
        startActivity(intent)
        finish()
    }


}