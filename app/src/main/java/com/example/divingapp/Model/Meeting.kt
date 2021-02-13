package com.example.divingapp.Model

class Meeting {

    var UserName: String? = null
    var UserSurname: String? = null
    var InstructorSurname: String? = null
    var InstructorName: String? = null
    var UserId: String? = null
    var InstructorId: String? = null
    var Date: String? = null
    var Time: String? = null
    var Info: String? = null

    constructor() {} // Needed for Firebase

    constructor(UserName: String?, UserSurname: String?, InstructorSurname: String?, InstructorName: String?, UserId: String?, InstructorId: String?, Date: String?, Time: String?, Info: String?)

}