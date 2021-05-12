package com.example.divingapp.activities.adminActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.divingapp.Model.User
import com.example.divingapp.R
import com.example.divingapp.View.IRegistrationView
import com.example.divingapp.activities.LoginActivity
import com.example.divingapp.activities.userActivities.UserHomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterInstructorActivity : AppCompatActivity() {

    private lateinit var fAuth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepeatedPassword: EditText
    private lateinit var btRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_instructor)

        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        etName = findViewById(R.id.et_administrator_register_name)
        etSurname = findViewById(R.id.et_administrator_register_surname)
        etEmail = findViewById(R.id.et_administrator_register_email)
        etPhoneNumber = findViewById(R.id.et_administrator_register_phone)
        etPassword = findViewById(R.id.et_administrator_register_password)
        etRepeatedPassword  = findViewById(R.id.et_administrator_register_repeat_password)
        btRegister  = findViewById(R.id.bt_administrator_register)

        btRegister.setOnClickListener {
            onRegister(etName, etSurname, etEmail, etPhoneNumber, etPassword, etRepeatedPassword, fAuth)
        }
    }

    private fun onRegisterResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccessfulRegistration(task: Task<AuthResult>, user: User) {
        val firebaseUser: FirebaseUser = task.result!!.user!!
        registerAllUserData(user, firebaseUser.uid)
    }

    private fun registerAllUserData(user: User, userId: String)
    {
        val reference: DatabaseReference = database.getReference("Instructors")

        reference.child(userId).child("Name").setValue(user.name);
        reference.child(userId).child("Surname").setValue(user.surname);
        reference.child(userId).child("Email").setValue(user.email);
        reference.child(userId).child("PhoneNumber").setValue(user.phoneNumber);
        reference.child(userId).child("Id").setValue(userId);
    }

    private fun onRegister(
        name: EditText,
        surname: EditText,
        email: EditText,
        phoneNumber: EditText,
        password: EditText,
        password2: EditText,
        firebaseAuth: FirebaseAuth
    ) {
        val user = User(
            name = name.text.toString().trim(),
            surname = surname.text.toString().trim(),
            email = email.text.toString().trim(),
            phoneNumber = phoneNumber.text.toString().trim(),
            password = password.text.toString().trim(),
            password2 = password2.text.toString().trim()
        )
        val registrationResult = user.isRegistrationDataValid()

        if(registrationResult) {
            firebaseAuth.createUserWithEmailAndPassword((user.email) as String, (user.password) as String).addOnCompleteListener(
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        onRegisterResult("Rejestracja powiodła się.")
                        onSuccessfulRegistration(task, user)
                    } else {
                        onRegisterResult(task.exception!!.message.toString())
                    }
                }
            )
        }
        else {
            onRegisterResult("Rejestracja nie powiodła się.")

            if(!user.isNameValid())
                name.error = "Name is not valid"
            if(!user.isSurnameValid())
                surname.error = "Surname is not valid"
            if(!user.isEmailValid())
                email.error = "Email is not valid"
            if(!user.isPhoneNumberValid())
                phoneNumber.error = "Phone number is not valid"
            if(!user.isPasswordValid())
                password.error = "Password is not valid"
            if(!user.arePasswordsMatches())
                password2.error = "Passwords are not the same"
        }
    }
}