package com.example.divingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.divingapp.Presenter.LoginPresenter
import com.example.divingapp.View.ILoginView

class MainActivity : AppCompatActivity(), ILoginView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val btLogin: Button = findViewById(R.id.bt_login)

        val loginPresenter = LoginPresenter(this)

        btLogin.setOnClickListener(View.OnClickListener {
            loginPresenter.onLogin(etEmail.toString(),etPassword.toString())
        })


    }

    override fun onLoginResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}