package com.example.divingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.divingapp.Presenter.HomePresenter
import com.example.divingapp.View.IHomeView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity(), IHomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homePresenter = HomePresenter(this)

        val btLogout: Button = findViewById(R.id.bt_logout)
        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email")

        // test
        var tv1: TextView = findViewById(R.id.textView)
        var tv2: TextView = findViewById(R.id.textView2)
        tv1.text = userId
        tv2.text = email
        //

        btLogout.setOnClickListener(View.OnClickListener {
            homePresenter.onLogout()
        })

    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}