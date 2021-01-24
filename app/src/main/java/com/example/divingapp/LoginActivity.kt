package com.example.divingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Presenter.LoginPresenter
import com.example.divingapp.View.ILoginView

class LoginActivity : AppCompatActivity(), ILoginView{

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val btLogin: Button = findViewById(R.id.bt_login)
        val tvRegister: TextView = findViewById(R.id.tv_register)
        progressBar = findViewById(R.id.progressBar)

        val loginPresenter = LoginPresenter(this)

        btLogin.setOnClickListener(View.OnClickListener {
            loginPresenter.onLogin(etEmail.text.toString(),etPassword.text.toString())
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
}