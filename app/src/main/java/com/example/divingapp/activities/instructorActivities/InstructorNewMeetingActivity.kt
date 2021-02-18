package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.divingapp.R
import com.example.divingapp.Utils.MeetingsRecyclerAdapter
import com.google.android.gms.common.util.UidVerifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class InstructorNewMeetingActivity : AppCompatActivity() {

    private lateinit var userId: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var btSave: Button
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_new_meeting)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        userId = intent.getStringExtra("userId")!!
        name = intent.getStringExtra("name")!!
        surname = intent.getStringExtra("surname")!!
        btSave = findViewById(R.id.bt_new_meeting_save)
        etDate = findViewById(R.id.et_instructor_new_meeting_date)
        etTime = findViewById(R.id.et_new_meeting_time)
        etDescription = findViewById(R.id.et_new_meeting_description)

        btSave.setOnClickListener {
            saveMeeting()
        }
    }

    private fun saveMeeting()
    {
        val meetingId = Random().nextInt().toString()
        val reference = database.getReference("Meetings").child(meetingId)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorNewMeetingActivity, "Wystąpił nieoczekiwany błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()) {
//                    Toast.makeText(this@InstructorNewMeetingActivity, "Wystąpił nieoczekiwany błąd. Spróbuj ponownie.", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
            }
        })
        setValues(meetingId, reference)

        Toast.makeText(this, "Dodane spotkanie.", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun setValues(meetingId: String, reference: DatabaseReference)
    {
        val instructorReference = database.getReference("Instructors").child(firebaseUser.uid)
        instructorReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorNewMeetingActivity, "Wystąpił nieoczekiwany błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val instructorName = snapshot.child("Name").value.toString()
                val instructorSurname = snapshot.child("Surname").value.toString()
                reference.child("InstructorName").setValue(instructorName)
                reference.child("InstructorSurname").setValue(instructorSurname)
            }

        })
        reference.child("Date").setValue(etDate.text.toString())
        reference.child("Info").setValue(etDescription.text.toString())
        reference.child("InstructorId").setValue(firebaseUser.uid)
        reference.child("Time").setValue(etTime.text.toString())
        reference.child("UserId").setValue(userId)
        reference.child("UserName").setValue(name)
        reference.child("UserSurname").setValue(surname)
        reference.child("Id").setValue(meetingId)
    }
}