package com.example.divingapp.activities.adminActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.UserData
import com.example.divingapp.R
import com.example.divingapp.Utils.UsersRecyclerAdapter
import com.example.divingapp.activities.instructorActivities.InstructorUserDetailsActivity
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class AdministratorInstructorsListActivity : AppCompatActivity(), UsersRecyclerAdapter.OnNoteListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var adapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator_instructors_list)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        val rvRecyclerView: RecyclerView = findViewById(R.id.rv_administrator_recyclerView)

        rvRecyclerView.layoutManager = LinearLayoutManager(this)

        val reference: DatabaseReference = database.getReference("Instructors")

        val query: Query = reference.orderByChild("Surname")
        val options: FirebaseRecyclerOptions<UserData> = FirebaseRecyclerOptions.Builder<UserData>()
            .setQuery(query, UserData::class.java)
            .build()

        adapter = UsersRecyclerAdapter(options, this)
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

    override fun onUserClick(position: Int, userId: String, name: String, surname: String) {
        Toast.makeText(this, "Nie można usunąć instruktora.", Toast.LENGTH_SHORT).show()
    }
}