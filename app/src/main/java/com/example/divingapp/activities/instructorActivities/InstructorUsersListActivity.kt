package com.example.divingapp.activities.instructorActivities

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.R
import com.example.divingapp.Utils.ListOfUsers
import com.example.divingapp.Utils.RecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class InstructorUsersListActivity : AppCompatActivity() {

    private var namesList = mutableListOf<String>()
    private var surnamesList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_users_list)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        val rvRecyclerView : RecyclerView = findViewById(R.id.rv_recyclerView)
        rvRecyclerView.layoutManager = LinearLayoutManager(this@InstructorUsersListActivity)
        rvRecyclerView.adapter = RecyclerAdapter(namesList, surnamesList, imagesList)


        readData(object : FirebaseCallback {
            override fun onCallbackList(namesList: List<String>, surnamesList: List<String>, imagesList: List<Int>){
                Log.d("FragmentActivity", namesList.toString())
            }

            override fun onCallback(name: String, surname: String, image: Int) {

                Log.d("FragmentActivity2", name.toString())
            }
        })




    }

    private fun addToList(name: String, surname: String, image: Int) {
        namesList.add(name)
        surnamesList.add(surname)
        imagesList.add(image)
    }


    private interface FirebaseCallback {
        fun onCallbackList(namesList: List<String>, surnamesList: List<String>, imagesList: List<Int> )
        fun onCallback(name: String, surname: String, image: Int )
    }

    private fun readData(callback: FirebaseCallback) {
        val instructorsReference: DatabaseReference = database.getReference("Instructors").child(firebaseUser.uid).child("Users")
        instructorsReference.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUsersListActivity, "Wystąpił nieoczekiwany błąd podczas pobierania danych", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for(userId in snapshot.children)
                {
                    val usersReference: DatabaseReference = database.getReference("Users").child(userId.value.toString())

                    usersReference.addValueEventListener(object  : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@InstructorUsersListActivity, "Wystąpił nieoczekiwany błąd podczas pobierania danych", Toast.LENGTH_SHORT).show()
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val name = snapshot.child("Name").value.toString()
                            val surname = snapshot.child("Surname").value.toString()

                            addToList(name, surname, 2)

                            callback.onCallback(name, surname, R.mipmap.ic_launcher_round)
                        }
                    })
                }
                callback.onCallbackList(namesList, surnamesList, imagesList)
            }
        })
    }
}