package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.Meeting
import com.example.divingapp.R
import com.example.divingapp.Utils.MeetingsRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class InstructorSingleUserMeetingsActivity : AppCompatActivity() {

    private lateinit var userId: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var adapter: MeetingsRecyclerAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var btNew: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_single_user_meetings)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        userId = intent.getStringExtra("userId")!!
        name = intent.getStringExtra("name")!!
        surname = intent.getStringExtra("surname")!!

        btNew = findViewById(R.id.bt_instructor_new_meeting)

        val rvRecyclerView: RecyclerView = findViewById(R.id.rv_single_user_meetings
        )
        rvRecyclerView.layoutManager = LinearLayoutManager(this)

        val reference: DatabaseReference = database.getReference("Meetings")

        val query: Query = reference.orderByChild("InstructorId").equalTo(firebaseUser.uid)
        val options: FirebaseRecyclerOptions<Meeting> = FirebaseRecyclerOptions.Builder<Meeting>()
                .setQuery(query, Meeting::class.java)
                .build()

        adapter = MeetingsRecyclerAdapter(options)
        rvRecyclerView.adapter = adapter

        btNew.setOnClickListener {
            val intent = Intent(this, InstructorNewMeetingActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("name", name)
            intent.putExtra("surname", surname)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}