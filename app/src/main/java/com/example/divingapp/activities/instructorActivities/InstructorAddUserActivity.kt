package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.divingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InstructorAddUserActivity : AppCompatActivity() {

    private lateinit var etAddUser: EditText
    private lateinit var btAddUser: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_add_user)

        etAddUser = findViewById(R.id.et_add_user)
        btAddUser = findViewById(R.id.bt_add_user)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!

        btAddUser.setOnClickListener {
            checkDataAndAddUser()
        }
    }

    private fun checkDataAndAddUser()
    {
        val reference = database.getReference("Users").child(etAddUser.text.toString())
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorAddUserActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(!snapshot.exists())
                {
                    Toast.makeText(this@InstructorAddUserActivity, "Użytkownik o podanym numerze nie istnieje.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    setInstructorId()
                    Toast.makeText(this@InstructorAddUserActivity, "Dodano Kursanta do listy.", Toast.LENGTH_SHORT).show()
                    etAddUser.setText("")
                }
            }
        })
    }

    private fun setInstructorId()
    {
        database.getReference("Users").child(etAddUser.text.toString()).child("InstructorId").setValue(firebaseUser.uid)
    }
}