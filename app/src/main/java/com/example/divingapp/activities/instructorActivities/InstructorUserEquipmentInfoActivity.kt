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

class InstructorUserEquipmentInfoActivity : AppCompatActivity() {

    private lateinit var btEquipment1: Button
    private lateinit var btEquipment2: Button
    private lateinit var btEquipment3: Button
    private lateinit var cbEquipment1: CheckBox
    private lateinit var cbEquipment2: CheckBox
    private lateinit var cbEquipment3: CheckBox
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_equipment_info)

        userId = intent.getStringExtra("userId")!!

        btEquipment1 = findViewById(R.id.bt_equipment_1)
        btEquipment2 = findViewById(R.id.bt_equipment_2)
        btEquipment3 = findViewById(R.id.bt_equipment_3)
        cbEquipment1 = findViewById(R.id.cb_equipment_1)
        cbEquipment2 = findViewById(R.id.cb_equipment_2)
        cbEquipment3 = findViewById(R.id.cb_equipment_3)

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
                Toast.makeText(this@InstructorUserEquipmentInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild("Equipment1"))
                    setCheckBoxValue(cbEquipment1, snapshot.child("Equipment1").value.toString())
                if(snapshot.hasChild("Equipment2"))
                    setCheckBoxValue(cbEquipment2, snapshot.child("Equipment2").value.toString())
                if(snapshot.hasChild("Equipment3"))
                    setCheckBoxValue(cbEquipment3, snapshot.child("Equipment3").value.toString())
            }
        })
    }

    private fun saveCheckBoxesValues()
    {
        cbEquipment1.setOnClickListener{
            val value: String = if(cbEquipment1.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Equipment1").setValue(value)
        }

        cbEquipment2.setOnClickListener{
            val value: String = if(cbEquipment2.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Equipment2").setValue(value)
        }

        cbEquipment3.setOnClickListener{
            val value: String = if(cbEquipment3.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Equipment3").setValue(value)
        }
    }

    private fun goToEquipmentDetailsLayout(skillName: String)
    {
        val intent = Intent(this, InstructorUserSkillDetailsActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("category", "Sprzęt")
        intent.putExtra("skill", skillName)
        startActivity(intent)
    }

    private fun setButtonsOnClickListeners()
    {
        btEquipment1.setOnClickListener {
            goToEquipmentDetailsLayout(btEquipment1.text.toString())
        }

        btEquipment2.setOnClickListener {
            goToEquipmentDetailsLayout(btEquipment2.text.toString())
        }

        btEquipment3.setOnClickListener {
            goToEquipmentDetailsLayout(btEquipment3.text.toString())
        }
    }
}

