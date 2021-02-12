package com.example.divingapp.Model

class Users {
    var Name: String? = null
    var Surname: String? = null

    constructor() {} // Needed for Firebase
    constructor(Name: String?, Surname: String?) {
        this.Name = Name
        this.Surname = Surname
    }



}