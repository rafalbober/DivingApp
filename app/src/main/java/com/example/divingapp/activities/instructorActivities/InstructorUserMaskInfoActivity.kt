package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.divingapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InstructorUserMaskInfoActivity : AppCompatActivity() {

    private lateinit var btMask1: Button
    private lateinit var btMask2: Button
    private lateinit var btMask3: Button
    private lateinit var btMask4: Button
    private lateinit var cbMask1: CheckBox
    private lateinit var cbMask2: CheckBox
    private lateinit var cbMask3: CheckBox
    private lateinit var cbMask4: CheckBox
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_mask_info)

        userId = intent.getStringExtra("userId")!!

        btMask1 = findViewById(R.id.bt_mask_1)
        btMask2 = findViewById(R.id.bt_mask_2)
        btMask3 = findViewById(R.id.bt_mask_3)
        btMask4 = findViewById(R.id.bt_mask_4)
        cbMask1 = findViewById(R.id.cb_mask_1)
        cbMask2 = findViewById(R.id.cb_mask_2)
        cbMask3 = findViewById(R.id.cb_mask_3)
        cbMask4 = findViewById(R.id.cb_mask_4)

        database = FirebaseDatabase.getInstance()

        setButtonsOnClickListeners()
    }

    override fun onStart() {
        super.onStart()
        setCheckBoxesValues()
        saveCheckBoxesValues()
    }

    private fun setCheckBoxValue(checkBox: CheckBox, value: String)
    {
        checkBox.isChecked = value == "1"
    }

    private fun setCheckBoxesValues()
    {
        val reference = database.getReference("Users").child(userId).child("Skills")
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUserMaskInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild("Mask1"))
                    setCheckBoxValue(cbMask1, snapshot.child("Mask1").value.toString())
                if(snapshot.hasChild("Mask2"))
                    setCheckBoxValue(cbMask2, snapshot.child("Mask2").value.toString())
                if(snapshot.hasChild("Mask3"))
                    setCheckBoxValue(cbMask3, snapshot.child("Mask3").value.toString())
                if(snapshot.hasChild("Mask4"))
                    setCheckBoxValue(cbMask4, snapshot.child("Mask4").value.toString())
            }
        })
    }

    private fun saveCheckBoxesValues()
    {
        cbMask1.setOnClickListener{
            val value: String = if(cbMask1.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Mask1").setValue(value)
        }

        cbMask2.setOnClickListener{
            val value: String = if(cbMask2.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Mask2").setValue(value)
        }

        cbMask3.setOnClickListener{
            val value: String = if(cbMask3.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Mask3").setValue(value)
        }

        cbMask4.setOnClickListener{
            val value: String = if(cbMask4.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Mask4").setValue(value)
        }
    }

    private fun goToMaskDetailsLayout()
    {
        val intent = Intent(this, InstructorUserSkillDetailsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun setButtonsOnClickListeners()
    {
        btMask1.setOnClickListener {
            goToMaskDetailsLayout()
        }

        btMask2.setOnClickListener {
            goToMaskDetailsLayout()
        }

        btMask3.setOnClickListener {
            goToMaskDetailsLayout()
        }

        btMask4.setOnClickListener {
            goToMaskDetailsLayout()
        }
    }
}