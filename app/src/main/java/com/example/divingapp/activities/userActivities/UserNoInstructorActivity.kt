package com.example.divingapp.activities.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.divingapp.R

class UserNoInstructorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_no_instructor)

        val etId: EditText= findViewById(R.id.et_user_no_instructor_uid)
        val userId = intent.getStringExtra("userId")

        etId.setText(userId)
    }
}