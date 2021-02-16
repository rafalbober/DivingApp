package com.example.divingapp.activities.instructorActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.divingapp.Presenter.classes.InstructorProfilePresenter
import com.example.divingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class InstructorUserInfoActivity : AppCompatActivity() {

    private lateinit var spBottle: Spinner
    private lateinit var spMoving: Spinner
    private lateinit var spJacket: Spinner
    private lateinit var spWarmer: Spinner
    private lateinit var spFoam: Spinner
    private lateinit var spFoam2: Spinner
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_user_info)

        spBottle = findViewById(R.id.sp_bottle)
        spMoving = findViewById(R.id.sp_moving)
        spJacket = findViewById(R.id.sp_jacket)
        spWarmer = findViewById(R.id.sp_warmer)
        spFoam = findViewById(R.id.sp_foam)
        spFoam2 = findViewById(R.id.sp_foam2)

        val btSave: Button = findViewById(R.id.bt_info_save)
        val btWeight: Button = findViewById(R.id.bt_info_weights)

        database = FirebaseDatabase.getInstance()
        userId = intent.getStringExtra("userId")!!

        btSave.setOnClickListener {
            saveSpinnersValues()
            onSaveResult("Zapisano zmiany.")
        }

        btWeight.setOnClickListener {
            goToWeightActivity()
        }

    }

    override fun onStart() {
        super.onStart()
        implementSpinnersAdapters()
        setSpinnersValues()
    }

    private fun implementSpinnersAdapters()
    {
        implementBottleAdapter()
        implementMovingAdapter()
        implementJacketAdapter()
        implementWarmerAdapter()
        implementFoamAdapter()
        implementFoam2Adapter()
    }

    private fun implementBottleAdapter()
    {
        val bottleAdapter = ArrayAdapter.createFromResource(this, R.array.bottle, android.R.layout.simple_spinner_item)
        bottleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBottle.adapter = bottleAdapter
    }

    private fun implementMovingAdapter()
    {
        val movingAdapter = ArrayAdapter.createFromResource(this, R.array.moving, android.R.layout.simple_spinner_item)
        movingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spMoving.adapter = movingAdapter
    }

    private fun implementJacketAdapter()
    {
        val jacketAdapter = ArrayAdapter.createFromResource(this, R.array.jacket, android.R.layout.simple_spinner_item)
        jacketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spJacket.adapter = jacketAdapter
    }

    private fun implementWarmerAdapter()
    {
        val jacketAdapter = ArrayAdapter.createFromResource(this, R.array.warmer, android.R.layout.simple_spinner_item)
        jacketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spWarmer.adapter = jacketAdapter
    }

    private fun implementFoamAdapter()
    {
        val foamAdapter = ArrayAdapter.createFromResource(this, R.array.foam, android.R.layout.simple_spinner_item)
        foamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFoam.adapter = foamAdapter
    }

    private fun implementFoam2Adapter()
    {
        val foam2Adapter = ArrayAdapter.createFromResource(this, R.array.foam2, android.R.layout.simple_spinner_item)
        foam2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFoam2.adapter = foam2Adapter
    }

    private fun setSpinnersValues()
    {
        val reference: DatabaseReference = database.getReference("Users").child(userId).child("Info")
        reference.addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@InstructorUserInfoActivity, "Wystąpił błąd podczas pobierania danych.", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    setSpinnerValue(spBottle, snapshot.child("Bottle").value.toString())
                    setSpinnerValue(spMoving, snapshot.child("Moving").value.toString())
                    setSpinnerValue(spJacket, snapshot.child("Jacket").value.toString())
                    setSpinnerValue(spWarmer, snapshot.child("Warmer").value.toString())
                    setSpinnerValue(spFoam, snapshot.child("FoamLength").value.toString())
                    setSpinnerValue(spFoam2, snapshot.child("FoamThickness").value.toString())
                }
            }

        })
    }

    private fun saveSpinnersValues()
    {
        val reference: DatabaseReference = database.getReference("Users").child(userId)
        reference.child("Info").child("Bottle").setValue(spBottle.selectedItemPosition.toString())
        reference.child("Info").child("Moving").setValue(spMoving.selectedItemPosition.toString())
        reference.child("Info").child("Jacket").setValue(spJacket.selectedItemPosition.toString())
        reference.child("Info").child("Warmer").setValue(spWarmer.selectedItemPosition.toString())
        reference.child("Info").child("FoamLength").setValue(spFoam.selectedItemPosition.toString())
        reference.child("Info").child("FoamThickness").setValue(spFoam2.selectedItemPosition.toString())
    }

    private fun setSpinnerValue(spinner: Spinner, pos: String)
    {
        spinner.setSelection(pos.toInt())
    }

    private fun onSaveResult(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToWeightActivity()
    {
        val intent = Intent(this, InstructorUserWeightsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

}