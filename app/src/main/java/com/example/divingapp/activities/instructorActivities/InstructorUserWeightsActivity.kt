package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.divingapp.Model.User
import com.example.divingapp.R
import com.google.firebase.database.*

class InstructorUserWeightsActivity : AppCompatActivity() {

    private lateinit var etJacket: EditText
    private lateinit var etBottle: EditText
    private lateinit var etBelt: EditText
    private lateinit var etLegs: EditText
    private lateinit var etDescription: EditText
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_weights)

        etJacket = findViewById(R.id.et_weight_jacket)
        etBottle = findViewById(R.id.et_weight_bottle)
        etBelt = findViewById(R.id.et_weight_belt)
        etLegs = findViewById(R.id.et_weight_legs)
        etDescription = findViewById(R.id.et_weight_descroption)

        database = FirebaseDatabase.getInstance()
        userId  = intent.getStringExtra("userId")!!
    }

    override fun onStart() {
        super.onStart()
        retrieveAndSetEditTexts()
    }

    override fun onStop() {
        super.onStop()
        saveUserData()
    }

    private fun setEditTextValue(editText: EditText, value: String)
    {
        editText.setText(value)
    }

    private fun getJacketValue(): String {
        return etJacket.text.toString().trim()
    }

    private fun getBottleValue(): String {
        return etBottle.text.toString().trim()
    }

    private fun getBeltValue(): String {
        return etBelt.text.toString().trim()
    }

    private fun getLegsValue(): String {
        return etLegs.text.toString().trim()
    }

    private fun getDescriptionValue(): String {
        return etDescription.text.toString().trim()
    }

    private fun saveUserData() {
        val reference: DatabaseReference = database.getReference("Users").child(userId)

        reference.child("Weights").child("Jacket").setValue(getJacketValue())
        reference.child("Weights").child("Bottle").setValue(getBottleValue())
        reference.child("Weights").child("Belt").setValue(getBeltValue())
        reference.child("Weights").child("Legs").setValue(getLegsValue())
        reference.child("Weights").child("Description").setValue(getDescriptionValue())
    }

    private fun retrieveAndSetEditTexts()
    {
        val reference: DatabaseReference = database.getReference("Users").child(userId).child("Weights")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUserWeightsActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    setEditTextValue(etJacket, snapshot.child("Jacket").value.toString())
                    setEditTextValue(etBottle, snapshot.child("Bottle").value.toString())
                    setEditTextValue(etBelt, snapshot.child("Belt").value.toString())
                    setEditTextValue(etLegs, snapshot.child("Legs").value.toString())
                    setEditTextValue(etDescription, snapshot.child("Description").value.toString())
                }
            }
        })
    }
}

