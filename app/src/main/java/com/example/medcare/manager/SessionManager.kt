package com.example.medcare.manager
import com.example.medcare.models.User

object SessionManager {
    var loggedInEmail: String?=null
    var currentUser: User?=null
    fun logout(){
        loggedInEmail=null
        currentUser=null
    }
}