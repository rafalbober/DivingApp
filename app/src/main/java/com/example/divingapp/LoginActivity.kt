package com.example.divingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.divingapp.Presenter.LoginPresenter
import com.example.divingapp.View.ILoginView

class LoginActivity : AppCompatActivity(), ILoginView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val btLogin: Button = findViewById(R.id.bt_login)
        val tvRegister: TextView = findViewById(R.id.tv_register)

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
}