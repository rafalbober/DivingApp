package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.divingapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InstructorUserSkillDetailsActivity : AppCompatActivity() {

    private lateinit var tvCategory: TextView
    private lateinit var tvSkill: TextView
    private lateinit var etDescription: EditText
    private lateinit var cb1: CheckBox
    private lateinit var cb2: CheckBox
    private lateinit var cb3: CheckBox
    private lateinit var cb4: CheckBox
    private lateinit var category: String
    private lateinit var skill: String
    private lateinit var userId: String
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_skill_details)

        tvCategory = findViewById(R.id.tv_skill_category)
        tvSkill = findViewById(R.id.tv_skill)
        etDescription = findViewById(R.id.et_skill_details_description)
        cb1 = findViewById(R.id.cb_skill_details)
        cb2 = findViewById(R.id.cb_skill_details2)
        cb3 = findViewById(R.id.cb_skill_details3)
        cb4 = findViewById(R.id.cb_skill_details4)

        database = FirebaseDatabase.getInstance()

        userId = intent.getStringExtra("userId")!!
        category = intent.getStringExtra("category")!!
        skill = intent.getStringExtra("skill")!!

        tvCategory.text = category
        tvSkill.text = skill

        retrieveDataAndSetValues()
        saveCheckBoxesValues()
    }

    private fun setCheckBoxValue(value: String)
    {
        when (value)
        {
            "1" -> cb1.isChecked = true
            "2" -> cb2.isChecked = true
            "3" -> cb3.isChecked = true
            "4" -> cb4.isChecked = true
        }
    }

    private fun retrieveDataAndSetValues()
    {
        val childName = generateChildName()
        val reference = database.getReference("Users").child(userId).child("SkillsDetails").child(childName)
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUserSkillDetailsActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    setCheckBoxValue(snapshot.toString())
                }
            }

        })
    }

    private fun generateChildName() : String
    {
        when (skill)
        {
            "Kokos" -> return "1a"
            "obrót plecy brzuch z automatem w ustach" -> return "1b"
            "manometr" -> return "1c"
            "znaki" -> return "1d"
            "płynięcie w sprzęcie po powierzchni 50m" -> return "1e"
            "holowanie zmęczonego nurka 25m" -> return "1f"
            "brak powietrza – dawca" -> return "2a"
            "brak powietrza – biorca" -> return "2b"
            "cesa w poziomie" -> return "2c"
            "piwot" -> return "2d"
            "hoover" -> return "2e"
            "brak powietrza +  wynurzenie" -> return "2f"
            "brak powietrza + wynurzenie + napełnienie BCD ustami" -> return "2g"
            "wynurzenie 9m/min" -> return "2h"
            "częściowo zalana" -> return "3a"
            "całkowicie zalana" -> return "3b"
            "zdjęcie i założenie" -> return "3c"
            "płynięcie bez maski 15m" -> return "3d"
            "odszukać przez zagarnięcie" -> return "4a"
            "odszukać po wężach" -> return "4b"
            "oczyścić bypassem" -> return "4c"
            "oczyścić wydechem" -> return "4d"
            "oddychanie ze wzbudzonego" -> return "4e"
            "złożenie" -> return "5a"
            "sprawdzenie" -> return "5b"
            "założenie" -> return "5c"
            else ->
            {
                Log.d("ERROR", "generateChildName: skill: " + skill)
                return "Nieznana umiejętność"
            }
        }
    }

    private fun saveCheckBoxesValues()
    {
        val childName = generateChildName()
        val reference = database.getReference("Users").child(userId).child("SkillsDetails").child(childName)

        cb1.setOnClickListener {
            cb2.isChecked = false
            cb3.isChecked = false
            cb4.isChecked = false

            reference.setValue("1")
        }

        cb2.setOnClickListener {
            cb1.isChecked = false
            cb3.isChecked = false
            cb4.isChecked = false

            reference.setValue("2")
        }

        cb3.setOnClickListener {
            cb2.isChecked = false
            cb1.isChecked = false
            cb4.isChecked = false

            reference.setValue("3")
        }

        cb4.setOnClickListener {
            cb2.isChecked = false
            cb3.isChecked = false
            cb1.isChecked = false

            reference.setValue("4")
        }
    }
}