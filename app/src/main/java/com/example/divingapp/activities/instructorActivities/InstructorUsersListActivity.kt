package com.example.divingapp.activities.instructorActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.UserData
import com.example.divingapp.R
import com.example.divingapp.Utils.RecyclerAdapterUsers
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query


class InstructorUsersListActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var adapter: RecyclerAdapterUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_users_list)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        val rvRecyclerView: RecyclerView = findViewById(R.id.rv_recyclerView)
        rvRecyclerView.layoutManager = LinearLayoutManager(this)

        val reference: DatabaseReference = database.getReference("Users")

        val query: Query = reference.orderByChild("InstructorId").equalTo(firebaseUser.uid)
        val options: FirebaseRecyclerOptions<UserData> = FirebaseRecyclerOptions.Builder<UserData>()
            .setQuery(query, UserData::class.java)
            .build()

        adapter = RecyclerAdapterUsers(options)
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


