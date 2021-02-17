package com.example.divingapp.activities.userActivities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.classes.ProfilePresenter
import com.example.divingapp.R
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfileActivity : AppCompatActivity(), IInstructorProfileView {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var database: FirebaseDatabase
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btSave: Button
    private lateinit var btReset: Button
    private lateinit var profilePresenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        profilePresenter = ProfilePresenter(this)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseUser = auth.currentUser!!

        etName = findViewById(R.id.et_user_profile_name)
        etSurname = findViewById(R.id.et_user_profile_surname)
        etEmail = findViewById(R.id.et_user_profile_email)
        etPhone = findViewById(R.id.et_user_profile_phone)
        btSave = findViewById(R.id.bt_user_profile_save)
        btReset = findViewById(R.id.bt_user_profile_reset)

        btSave.setOnClickListener {
            profilePresenter.onSave(firebaseUser, database)
        }

        btReset.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Podaj swój adres e-mail.")
            val view: View = layoutInflater.inflate(R.layout.reset_password, null)
            val email = view.findViewById<EditText>(R.id.et_reset)
            builder.setView(view)
            builder.setPositiveButton("Zresetuj hasło.", DialogInterface.OnClickListener { _, _ ->
                resetPassword(email)
            })
            builder.setNegativeButton("Wróć do profilu.", DialogInterface.OnClickListener { _, _ ->
            })
            builder.show()
        }

    }

    override fun onStart() {
        super.onStart()
        profilePresenter.onStart(firebaseUser, database, "Users")
    }

    private fun resetPassword(email: EditText)
    {
        if(Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
            auth.sendPasswordResetEmail(email.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "E-mail został wysłany.", Toast.LENGTH_SHORT).show()
                        }
                    }
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
            etEmail.error = "Podany adres e-mail jest niepoprawny."
            isUserDataValid = false
        }
        if(!user.isPhoneNumberValid()) {
            etPhone.error = "Podany numer telefonu jest niepoprawny."
            isUserDataValid = false
        }
        return isUserDataValid
    }

    override fun editUserData(user: User) {
        val reference: DatabaseReference = database.getReference("Users").child(firebaseUser.uid)

        reference.child("Name").setValue(user.name);
        reference.child("Surname").setValue(user.surname);
        reference.child("Email").setValue(user.email);
        reference.child("PhoneNumber").setValue(user.phoneNumber);

        firebaseUser.updateEmail(user.email!!)
    }
}