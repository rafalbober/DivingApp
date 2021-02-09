package com.example.divingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divingapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btLogin: Button = findViewById(R.id.bt_home_login)
        val btRegister: Button = findViewById(R.id.bt_home_register)

        btLogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        })

        btRegister.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        })
    }
}