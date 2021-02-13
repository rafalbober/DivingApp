package com.example.divingapp.activities.instructorActivities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.divingapp.Presenter.classes.InstructorUserDetailsPresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorUserDetailsView

class InstructorUserDetailsActivity : AppCompatActivity(), IInstructorUserDetailsView {

    lateinit var tvName: TextView
    lateinit var btWeight: Button
    lateinit var btSkills: Button
    lateinit var btContact: Button
    lateinit var name: String
    lateinit var surname: String
    lateinit var userId: String
    lateinit var instructorUserDetailsPresenter: InstructorUserDetailsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_details)

        instructorUserDetailsPresenter = InstructorUserDetailsPresenter(this)

        tvName = findViewById(R.id.tv_user_details_name)
        btWeight = findViewById(R.id.bt_user_details_weight)
        btSkills = findViewById(R.id.bt_user_details_skills)
        btContact = findViewById(R.id.bt_user_details_contact)

        name = intent.getStringExtra("name")!!
        surname = intent.getStringExtra("surname")!!
        userId = intent.getStringExtra("userId")!!

        Log.d("OnCreate", "$name $surname $userId")

        btWeight.setOnClickListener{
            goToWeightsActivity()
        }

        btSkills.setOnClickListener{
            goToSkillsActivity()
        }

        btContact.setOnClickListener{
            goToContactActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        setUserName()
    }

    @SuppressLint("SetTextI18n")
    override fun setUserName() {
        tvName.text = "$name $surname"
    }

    override fun goToWeightsActivity() {
        val intent = Intent(this, InstructorUserInfoActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    override fun goToSkillsActivity() {
        val intent = Intent(this, InstructorUserSkillsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    override fun goToContactActivity() {
        val intent = Intent(this, InstructorUserContactActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
}