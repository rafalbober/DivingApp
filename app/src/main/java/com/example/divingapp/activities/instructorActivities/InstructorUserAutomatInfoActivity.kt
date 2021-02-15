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

class InstructorUserAutomatInfoActivity : AppCompatActivity() {

    private lateinit var btAutomat1: Button
    private lateinit var btAutomat2: Button
    private lateinit var btAutomat3: Button
    private lateinit var btAutomat4: Button
    private lateinit var btAutomat5: Button
    private lateinit var cbAutomat1: CheckBox
    private lateinit var cbAutomat2: CheckBox
    private lateinit var cbAutomat3: CheckBox
    private lateinit var cbAutomat4: CheckBox
    private lateinit var cbAutomat5: CheckBox
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_automat_info)

        userId = intent.getStringExtra("userId")!!

        btAutomat1 = findViewById(R.id.bt_automat_1)
        btAutomat2 = findViewById(R.id.bt_automat_2)
        btAutomat3 = findViewById(R.id.bt_automat_3)
        btAutomat4 = findViewById(R.id.bt_automat_4)
        btAutomat5 = findViewById(R.id.bt_automat_5)
        cbAutomat1 = findViewById(R.id.cb_automat_1)
        cbAutomat2 = findViewById(R.id.cb_automat_2)
        cbAutomat3 = findViewById(R.id.cb_automat_3)
        cbAutomat4 = findViewById(R.id.cb_automat_4)
        cbAutomat5 = findViewById(R.id.cb_automat_5)

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
                Toast.makeText(this@InstructorUserAutomatInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild("Automat1"))
                    setCheckBoxValue(cbAutomat1, snapshot.child("Automat1").value.toString())
                if(snapshot.hasChild("Automat2"))
                    setCheckBoxValue(cbAutomat2, snapshot.child("Automat2").value.toString())
                if(snapshot.hasChild("Automat3"))
                    setCheckBoxValue(cbAutomat3, snapshot.child("Automat3").value.toString())
                if(snapshot.hasChild("Automat4"))
                    setCheckBoxValue(cbAutomat4, snapshot.child("Automat4").value.toString())
                if(snapshot.hasChild("Automat5"))
                    setCheckBoxValue(cbAutomat5, snapshot.child("Automat5").value.toString())
            }
        })
    }

    private fun saveCheckBoxesValues()
    {
        cbAutomat1.setOnClickListener{
            val value: String = if(cbAutomat1.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Automat1").setValue(value)
        }

        cbAutomat2.setOnClickListener{
            val value: String = if(cbAutomat2.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Automat2").setValue(value)
        }

        cbAutomat3.setOnClickListener{
            val value: String = if(cbAutomat3.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Automat3").setValue(value)
        }

        cbAutomat4.setOnClickListener{
            val value: String = if(cbAutomat4.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Automat4").setValue(value)
        }

        cbAutomat5.setOnClickListener{
            val value: String = if(cbAutomat5.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Automat5").setValue(value)
        }
    }

    private fun goToAutomatDetailsLayout(skillName: String)
    {
        val intent = Intent(this, InstructorUserSkillDetailsActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("category", "Automat")
        intent.putExtra("skill", skillName)
        startActivity(intent)
    }

    private fun setButtonsOnClickListeners()
    {
        btAutomat1.setOnClickListener {
            goToAutomatDetailsLayout(btAutomat1.text.toString())
        }

        btAutomat2.setOnClickListener {
            goToAutomatDetailsLayout(btAutomat2.text.toString())
        }

        btAutomat3.setOnClickListener {
            goToAutomatDetailsLayout(btAutomat3.text.toString())
        }

        btAutomat4.setOnClickListener {
            goToAutomatDetailsLayout(btAutomat4.text.toString())
        }

        btAutomat5.setOnClickListener {
            goToAutomatDetailsLayout(btAutomat5.text.toString())
        }
    }
}
