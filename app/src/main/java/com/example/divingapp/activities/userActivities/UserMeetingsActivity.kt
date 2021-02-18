package com.example.divingapp.activities.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class UserMeetingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var adapter: MeetingsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_meetings)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        val rvRecyclerView: RecyclerView = findViewById(R.id.rv_user_meetings)
        rvRecyclerView.layoutManager = LinearLayoutManager(this)

        val reference: DatabaseReference = database.getReference("Meetings")

        val query: Query = reference.orderByChild("UserId").equalTo(firebaseUser.uid)
        val options: FirebaseRecyclerOptions<Meeting> = FirebaseRecyclerOptions.Builder<Meeting>()
            .setQuery(query, Meeting::class.java)
            .build()

        adapter = MeetingsRecyclerAdapter(options)
        rvRecyclerView.adapter = adapter

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