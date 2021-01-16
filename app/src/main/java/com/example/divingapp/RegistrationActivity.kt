package com.example.divingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.divingapp.Presenter.RegistrationPresenter
import com.example.divingapp.View.IRegistrationView

class RegistrationActivity : AppCompatActivity(), IRegistrationView {
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

        val registrationPresenter = RegistrationPresenter(this)

        btRegister.setOnClickListener(View.OnClickListener {
            registrationPresenter.onRegister(etName.text.toString(),etSurname.text.toString(), etEmail.text.toString(), etPhoneNumber.text.toString(), etPassword.text.toString(), etPassword2.text.toString())
        })

        tvLogin.setOnClickListener(View.OnClickListener {
            setContentView(R.layout.activity_login)
        })



    }

    override fun onRegisterResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun goToLoginLayout() {
        setContentView(R.layout.activity_login)
    }
}