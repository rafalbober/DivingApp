package com.example.divingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Presenter.LoginPresenter
import com.example.divingapp.View.ILoginView
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity(), ILoginView{

    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth : FirebaseAuth

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
        startActivity(Intent(applicationContext, HomeActivity::class.java))
    }

    override fun makeProgressBarVisible() {
        progressBar.visibility = View.VISIBLE
    }

    override fun makeProgressBarInvisible() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onSuccessfulLogin(task: Task<AuthResult>) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        val intent: Intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("user_id", firebaseUser.uid)
        intent.putExtra("email", firebaseUser.email)
        startActivity(intent)
        finish()
    }
}