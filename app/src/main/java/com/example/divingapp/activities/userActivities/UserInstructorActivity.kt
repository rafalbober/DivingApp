package com.example.divingapp.activities.userActivities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.divingapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserInstructorActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var tvNameAndSurname: TextView
    private lateinit var etPhoneNumber: EditText
    private lateinit var etEmail: EditText
    private lateinit var instructorId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_instructor)

        database = FirebaseDatabase.getInstance()
        instructorId = intent.getStringExtra("instructorId")!!

        tvNameAndSurname = findViewById(R.id.tv_user_instructor_name)
        etEmail = findViewById(R.id.et_user_instructor_email)
        etPhoneNumber = findViewById(R.id.et_user_instructor_phone)

        setAllValues()
    }

    private fun setAllValues()
    {
        val reference = database.getReference("Instructors").child(instructorId)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserInstructorActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                tvNameAndSurname.text = snapshot.child("Name").value.toString() + " " + snapshot.child("Surname").value.toString()
                etEmail.setText(snapshot.child("Email").value.toString())
                etPhoneNumber.setText(snapshot.child("PhoneNumber").value.toString())
            }

        })
    }
}