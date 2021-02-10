package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.divingapp.Presenter.classes.InstructorHomePresenter
import com.example.divingapp.Presenter.classes.InstructorProfilePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorProfileView

class InstructorProfileActivity : AppCompatActivity(), IInstructorProfileView {

    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btSave: Button
    private lateinit var btReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_profile)

        val instructorProfilePresenter = InstructorProfilePresenter(this)

        etName = findViewById(R.id.et_name_instructor_profile)
        etSurname = findViewById(R.id.et_surname_instructor_profile)
        etEmail = findViewById(R.id.et_email_instructor_profile)
        etPhone = findViewById(R.id.et_phone_instructor_profile)
        btSave = findViewById(R.id.bt_save_instructor)
        btReset = findViewById(R.id.bt_reset_instructor)

//        val userId = intent.getStringExtra("user_id")
//        val email = intent.getStringExtra("email")


        instructorProfilePresenter.onCreate("stgrievre")

    }

    override fun setEditTextsValues(name: String, surname: String, email: String, phone: String)
    {
        etName.setText(name)
        etSurname.setText(surname)
        etEmail.setText(email)
        etPhone.setText(phone)
    }
}