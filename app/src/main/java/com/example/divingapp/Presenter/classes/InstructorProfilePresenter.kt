package com.example.divingapp.Presenter.classes

import android.widget.EditText
import android.widget.Toast
import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.Iinterfaces.IInstructorHomePresenter
import com.example.divingapp.Presenter.Iinterfaces.IInstructorProfilePresenter
import com.example.divingapp.View.IInstructorHomeView
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class InstructorProfilePresenter(override val instructorProfileView: IInstructorProfileView) :
        IInstructorProfilePresenter {

    override fun onStart(firebaseUser: FirebaseUser, database: FirebaseDatabase) {

        val instructorsReference: DatabaseReference = database.getReference("Instructors").child(firebaseUser.uid)
        instructorsReference.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                instructorProfileView.saveDataResult("Wystąpił nieoczekiwany błąd podczas pobierania danych")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val name: String = snapshot.child("Name").value.toString()
                val surname: String = snapshot.child("Surname").value.toString()
                val email: String = snapshot.child("Email").value.toString()
                val phone: String = snapshot.child("PhoneNumber").value.toString()

                instructorProfileView.setEditTextsValues(name, surname, email, phone)
            }
        })
    }

    override fun onSave(firebaseUser: FirebaseUser, database: FirebaseDatabase) {
        val user = User(instructorProfileView.getName(), instructorProfileView.getSurname(), instructorProfileView.getEmail(), instructorProfileView.getPhone())
        if(!instructorProfileView.validUserData(user))
            instructorProfileView.saveDataResult("Zapisywanie danych nie powiodło się.")
        else
        {
            instructorProfileView.editUserData(user)
            instructorProfileView.saveDataResult("Dane zostały zaktualizowane prawidłowo.")
        }
    }


}