package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.divingapp.R

class InstructorUserSkillsActivity : AppCompatActivity() {

    lateinit var btSkills: Button
    lateinit var btMask: Button
    lateinit var btAutomat: Button
    lateinit var btEquipment: Button
    lateinit var btSwimming: Button
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_skills)

        btSkills = findViewById(R.id.bt_user_skills_info)
        btMask = findViewById(R.id.bt_user_mask_info)
        btAutomat = findViewById(R.id.bt_user_automat_info)
        btEquipment = findViewById(R.id.bt_user_equipment_info)
        btSwimming = findViewById(R.id.bt_user_swimming_info)

        userId = intent.getStringExtra("userId")!!
        Log.d("UserSkills", userId)

        btSkills.setOnClickListener {
            goToSkillsActivity()
        }

        btMask.setOnClickListener {
            goToMaskActivity()
        }

        btAutomat.setOnClickListener {
            goToAutomatActivity()
        }

        btEquipment.setOnClickListener {
            goToEquipmentActivity()
        }

        btSwimming.setOnClickListener {
            goToSwimmingActivity()
        }
    }

    private fun goToSkillsActivity()
    {
        val intent = Intent(this, InstructorUserSkillsInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun goToMaskActivity()
    {
        val intent = Intent(this, InstructorUserMaskInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun goToAutomatActivity()
    {
        val intent = Intent(this, InstructorUserAutomatInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun goToEquipmentActivity()
    {
        val intent = Intent(this, InstructorUserEquipmentInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    private fun goToSwimmingActivity()
    {
        val intent = Intent(this, InstructorUserSwimmingInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
}