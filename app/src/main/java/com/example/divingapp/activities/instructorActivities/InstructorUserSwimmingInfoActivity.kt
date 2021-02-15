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

class InstructorUserSwimmingInfoActivity : AppCompatActivity() {

    private lateinit var btSwimming1: Button
    private lateinit var btSwimming2: Button
    private lateinit var btSwimming3: Button
    private lateinit var btSwimming4: Button
    private lateinit var btSwimming5: Button
    private lateinit var btSwimming6: Button
    private lateinit var btSwimming7: Button
    private lateinit var btSwimming8: Button
    private lateinit var cbSwimming1: CheckBox
    private lateinit var cbSwimming2: CheckBox
    private lateinit var cbSwimming3: CheckBox
    private lateinit var cbSwimming4: CheckBox
    private lateinit var cbSwimming5: CheckBox
    private lateinit var cbSwimming6: CheckBox
    private lateinit var cbSwimming7: CheckBox
    private lateinit var cbSwimming8: CheckBox
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_swimming_info)

        userId = intent.getStringExtra("userId")!!

        btSwimming1 = findViewById(R.id.bt_swimming_1)
        btSwimming2 = findViewById(R.id.bt_swimming_2)
        btSwimming3 = findViewById(R.id.bt_swimming_3)
        btSwimming4 = findViewById(R.id.bt_swimming_4)
        btSwimming5 = findViewById(R.id.bt_swimming_5)
        btSwimming6 = findViewById(R.id.bt_swimming_6)
        btSwimming7 = findViewById(R.id.bt_swimming_7)
        btSwimming8 = findViewById(R.id.bt_swimming_8)
        cbSwimming1 = findViewById(R.id.cb_swimming_1)
        cbSwimming2 = findViewById(R.id.cb_swimming_2)
        cbSwimming3 = findViewById(R.id.cb_swimming_3)
        cbSwimming4 = findViewById(R.id.cb_swimming_4)
        cbSwimming5 = findViewById(R.id.cb_swimming_5)
        cbSwimming6 = findViewById(R.id.cb_swimming_6)
        cbSwimming7 = findViewById(R.id.cb_swimming_7)
        cbSwimming8 = findViewById(R.id.cb_swimming_8)

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
                Toast.makeText(this@InstructorUserSwimmingInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild("Swimming1"))
                    setCheckBoxValue(cbSwimming1, snapshot.child("Swimming1").value.toString())
                if(snapshot.hasChild("Swimming2"))
                    setCheckBoxValue(cbSwimming2, snapshot.child("Swimming2").value.toString())
                if(snapshot.hasChild("Swimming3"))
                    setCheckBoxValue(cbSwimming3, snapshot.child("Swimming3").value.toString())
                if(snapshot.hasChild("Swimming4"))
                    setCheckBoxValue(cbSwimming4, snapshot.child("Swimming4").value.toString())
                if(snapshot.hasChild("Swimming5"))
                    setCheckBoxValue(cbSwimming5, snapshot.child("Swimming5").value.toString())
                if(snapshot.hasChild("Swimming6"))
                    setCheckBoxValue(cbSwimming6, snapshot.child("Swimming6").value.toString())
                if(snapshot.hasChild("Swimming7"))
                    setCheckBoxValue(cbSwimming7, snapshot.child("Swimming7").value.toString())
                if(snapshot.hasChild("Swimming8"))
                    setCheckBoxValue(cbSwimming8, snapshot.child("Swimming8").value.toString())
            }
        })
    }

    private fun saveCheckBoxesValues()
    {
        cbSwimming1.setOnClickListener{
            val value: String = if(cbSwimming1.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming1").setValue(value)
        }

        cbSwimming2.setOnClickListener{
            val value: String = if(cbSwimming2.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming2").setValue(value)
        }

        cbSwimming3.setOnClickListener{
            val value: String = if(cbSwimming3.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming3").setValue(value)
        }

        cbSwimming4.setOnClickListener{
            val value: String = if(cbSwimming4.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming4").setValue(value)
        }

        cbSwimming5.setOnClickListener{
            val value: String = if(cbSwimming5.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming5").setValue(value)
        }

        cbSwimming6.setOnClickListener{
            val value: String = if(cbSwimming6.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming6").setValue(value)
        }

        cbSwimming7.setOnClickListener{
            val value: String = if(cbSwimming7.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming7").setValue(value)
        }

        cbSwimming8.setOnClickListener{
            val value: String = if(cbSwimming8.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Swimming8").setValue(value)
        }
    }

    private fun goToSwimmingDetailsLayout()
    {
        val intent = Intent(this, InstructorUserSkillDetailsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun setButtonsOnClickListeners()
    {
        btSwimming1.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming2.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming3.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming4.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming5.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming6.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming7.setOnClickListener {
            goToSwimmingDetailsLayout()
        }

        btSwimming8.setOnClickListener {
            goToSwimmingDetailsLayout()
        }
    }
}