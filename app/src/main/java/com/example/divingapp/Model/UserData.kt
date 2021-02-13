package com.example.divingapp.Model

class UserData {
    var Name: String? = null
    var Surname: String? = null
    var Id: String? = null
    var PhoneNumber: String? = null
    var InstructorId: String? = null
    var Email: String? = null

    constructor() {} // Needed for Firebase
    constructor(Name: String?, Surname: String?, Id: String?, PhoneNumber: String?, InsrtuctorId: String?, Email: String?) {
        this.Name = Name
        this.Surname = Surname
        this.Id = Id
        this.PhoneNumber = PhoneNumber
        this.InstructorId = InsrtuctorId
        this.Email = Email
    }
}