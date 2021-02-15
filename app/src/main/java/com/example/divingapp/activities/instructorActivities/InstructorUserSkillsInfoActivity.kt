package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Toast
import com.example.divingapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InstructorUserSkillsInfoActivity : AppCompatActivity() {

    private lateinit var btSkill1: Button
    private lateinit var btSkill2: Button
    private lateinit var btSkill3: Button
    private lateinit var btSkill4: Button
    private lateinit var btSkill5: Button
    private lateinit var btSkill6: Button
    private lateinit var cbSkill1: CheckBox
    private lateinit var cbSkill2: CheckBox
    private lateinit var cbSkill3: CheckBox
    private lateinit var cbSkill4: CheckBox
    private lateinit var cbSkill5: CheckBox
    private lateinit var cbSkill6: CheckBox
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_skills_info)

        userId = intent.getStringExtra("userId")!!

        btSkill1 = findViewById(R.id.bt_skill_1)
        btSkill2 = findViewById(R.id.bt_skill_2)
        btSkill3 = findViewById(R.id.bt_skill_3)
        btSkill4 = findViewById(R.id.bt_skill_4)
        btSkill5 = findViewById(R.id.bt_skill_5)
        btSkill6 = findViewById(R.id.bt_skill_6)
        cbSkill1 = findViewById(R.id.cb_skill_1)
        cbSkill2 = findViewById(R.id.cb_skill_2)
        cbSkill3 = findViewById(R.id.cb_skill_3)
        cbSkill4 = findViewById(R.id.cb_skill_4)
        cbSkill5 = findViewById(R.id.cb_skill_5)
        cbSkill6 = findViewById(R.id.cb_skill_6)

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
                Toast.makeText(this@InstructorUserSkillsInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChild("Skill1"))
                    setCheckBoxValue(cbSkill1, snapshot.child("Skill1").value.toString())
                if(snapshot.hasChild("Skill2"))
                    setCheckBoxValue(cbSkill2, snapshot.child("Skill2").value.toString())
                if(snapshot.hasChild("Skill3"))
                    setCheckBoxValue(cbSkill3, snapshot.child("Skill3").value.toString())
                if(snapshot.hasChild("Skill4"))
                    setCheckBoxValue(cbSkill4, snapshot.child("Skill4").value.toString())
                if(snapshot.hasChild("Skill5"))
                    setCheckBoxValue(cbSkill5, snapshot.child("Skill5").value.toString())
                if(snapshot.hasChild("Skill6"))
                    setCheckBoxValue(cbSkill6, snapshot.child("Skill6").value.toString())
            }
        })
    }

    private fun saveCheckBoxesValues()
    {
        cbSkill1.setOnClickListener{
            val value: String = if(cbSkill1.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill1").setValue(value)
        }

        cbSkill2.setOnClickListener{
            val value: String = if(cbSkill2.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill2").setValue(value)
        }

        cbSkill3.setOnClickListener{
            val value: String = if(cbSkill3.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill3").setValue(value)
        }

        cbSkill4.setOnClickListener{
            val value: String = if(cbSkill4.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill4").setValue(value)
        }

        cbSkill5.setOnClickListener{
            val value: String = if(cbSkill5.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill5").setValue(value)
        }

        cbSkill6.setOnClickListener{
            val value: String = if(cbSkill6.isChecked)
                "1"
            else
                "0"

            database.getReference("Users").child(userId).child("Skills").child("Skill6").setValue(value)
        }
    }

    private fun goToSkillDetailsLayout()
    {
        val intent = Intent(this, InstructorUserSkillDetailsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun setButtonsOnClickListeners()
    {
        btSkill1.setOnClickListener {
            goToSkillDetailsLayout()
        }

        btSkill2.setOnClickListener {
            goToSkillDetailsLayout()
        }

        btSkill3.setOnClickListener {
            goToSkillDetailsLayout()
        }

        btSkill4.setOnClickListener {
            goToSkillDetailsLayout()
        }

        btSkill5.setOnClickListener {
            goToSkillDetailsLayout()
        }

        btSkill6.setOnClickListener {
            goToSkillDetailsLayout()
        }
    }
}