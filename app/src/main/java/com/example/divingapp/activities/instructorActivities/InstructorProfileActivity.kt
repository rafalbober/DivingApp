package com.example.divingapp.activities.instructorActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.classes.InstructorProfilePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class InstructorProfileActivity : AppCompatActivity(), IInstructorProfileView {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btSave: Button
    private lateinit var btReset: Button
    private lateinit var instructorProfilePresenter: InstructorProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_profile)

        instructorProfilePresenter = InstructorProfilePresenter(this)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        etName = findViewById(R.id.et_name_instructor_profile)
        etSurname = findViewById(R.id.et_surname_instructor_profile)
        etEmail = findViewById(R.id.et_email_instructor_profile)
        etPhone = findViewById(R.id.et_phone_instructor_profile)
        btSave = findViewById(R.id.bt_save_instructor)
        btReset = findViewById(R.id.bt_reset_instructor)

        btSave.setOnClickListener {
            instructorProfilePresenter.onSave(firebaseUser, database)
        }

    }

    override fun onStart() {
        super.onStart()
        instructorProfilePresenter.onStart(firebaseUser, database)
    }

    override fun setEditTextsValues(name: String, surname: String, email: String, phone: String)
    {
        etName.setText(name)
        etSurname.setText(surname)
        etEmail.setText(email)
        etPhone.setText(phone)
    }

    override fun saveDataResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun getName(): String {
        return etName.text.toString().trim()
    }

    override fun getSurname(): String {
        return etSurname.text.toString().trim()
    }

    override fun getEmail(): String {
        return etEmail.text.toString().trim()
    }

    override fun getPhone(): String {
        return etPhone.text.toString().trim()
    }

    override fun validUserData(user: User) : Boolean {
        var isUserDataValid: Boolean = true
        if(!user.isNameValid()) {
            etName.error = "Podane imię jest niepoprawne."
            isUserDataValid = false
        }
        if(!user.isSurnameValid()) {
            etSurname.error = "Podane nazwisko jest niepoprawne."
            isUserDataValid = false
        }
        if(!user.isEmailValid()) {
            etEmail.error = "Podany adres email jest niepoprawny."
            isUserDataValid = false
        }
        if(!user.isPhoneNumberValid()) {
            etPhone.error = "Podany numer telefonu jest niepoprawny."
            isUserDataValid = false
        }
        return isUserDataValid
    }

    override fun editUserData(user: User) {
        val reference: DatabaseReference = database.getReference("Instructors").child(firebaseUser.uid)

        reference.child("Name").setValue(user.name);
        reference.child("Surname").setValue(user.surname);
        reference.child("Email").setValue(user.email);
        reference.child("PhoneNumber").setValue(user.phoneNumber);

        firebaseUser.updateEmail(user.email!!)
    }
}