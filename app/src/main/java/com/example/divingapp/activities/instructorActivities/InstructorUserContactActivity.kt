package com.example.divingapp.activities.instructorActivities

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

class InstructorUserContactActivity : AppCompatActivity() {

    private lateinit var tvNameAndSurname: TextView
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var userId: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_contact)

        tvNameAndSurname = findViewById(R.id.tv_contact_name)
        etEmail = findViewById(R.id.et_contact_email)
        etPhone = findViewById(R.id.et_contact_phone)

        userId = intent.getStringExtra("userId")!!
        name = intent.getStringExtra("name")!!
        surname = intent.getStringExtra("surname")!!

        database = FirebaseDatabase.getInstance()
    }

    private fun setEditTextsValues()
    {
        val reference = database.getReference("Users").child(userId)
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUserContactActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                etEmail.setText(snapshot.child("Email").value.toString())
                etPhone.setText(snapshot.child("PhoneNumber").value.toString())
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setNameAndSurname()
    {
        tvNameAndSurname.text = "$name $surname"
    }
}