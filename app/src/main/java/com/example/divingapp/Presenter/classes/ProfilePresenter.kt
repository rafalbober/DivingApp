package com.example.divingapp.Presenter.classes

import com.example.divingapp.Model.User
import com.example.divingapp.Presenter.Iinterfaces.IProfilePresenter
import com.example.divingapp.View.IInstructorProfileView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ProfilePresenter(override val instructorUsersListView: IInstructorProfileView) :
        IProfilePresenter {

    override fun onStart(firebaseUser: FirebaseUser, database: FirebaseDatabase, userRole: String) {

        val instructorsReference: DatabaseReference = database.getReference(userRole).child(firebaseUser.uid)
        instructorsReference.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                instructorUsersListView.saveDataResult("Wystąpił nieoczekiwany błąd podczas pobierania danych")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val name: String = snapshot.child("Name").value.toString()
                val surname: String = snapshot.child("Surname").value.toString()
                val email: String = snapshot.child("Email").value.toString()
                val phone: String = snapshot.child("PhoneNumber").value.toString()

                instructorUsersListView.setEditTextsValues(name, surname, email, phone)
            }
        })
    }

    override fun onSave(firebaseUser: FirebaseUser, database: FirebaseDatabase) {
        val user = User(instructorUsersListView.getName(), instructorUsersListView.getSurname(), instructorUsersListView.getEmail(), instructorUsersListView.getPhone())
        if(!instructorUsersListView.validUserData(user))
            instructorUsersListView.saveDataResult("Zapisywanie danych nie powiodło się.")
        else
        {
            instructorUsersListView.editUserData(user)
            instructorUsersListView.saveDataResult("Dane zostały zaktualizowane prawidłowo.")
        }
    }


}