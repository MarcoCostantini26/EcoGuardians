package com.example.ecoguardians

import android.net.Uri
import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.User
import com.example.ecoguardians.data.UserBadge
import com.example.ecoguardians.data.UserDAO

class UserRepository(private val userDAO: UserDAO) {

   //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
   //By default Room runs suspend queries off the main thread
   @WorkerThread
   fun getUsername() : String {
       return userDAO.getUsername()
   }
    @WorkerThread
    fun getEmail() : String{
        return userDAO.getEmail()
    }

    @WorkerThread
    fun getProfilePicture() : Uri{
        return userDAO.getProfilePicture()
    }

    @WorkerThread
    suspend fun getUserBadges(email : String) : List<UserBadge> {
        return userDAO.getUserBadges(email)
    }

    @WorkerThread
    suspend fun getUsers() : List<User> {
        return userDAO.getUsers()
    }

    @WorkerThread
    suspend fun insertUser(item : User){
        userDAO.insert(item)
    }

    @WorkerThread
    fun doesUserExist(email : String) : Int{
        return userDAO.doesUserExist(email)
    }

    @WorkerThread
    fun isPasswordCorrect(email : String, password: String) : Int{
        return userDAO.isPasswordCorrect(email, password)
    }

    fun countUserInSession() : Int{
        return userDAO.countUserInSession()
    }

    @WorkerThread
    fun setSessionTrue(email : String){
        userDAO.setSessionTrue(email)
    }
    @WorkerThread
    fun setSessionFalse(email : String){
        userDAO.setSessionFalse(email)
    }

    @WorkerThread
    suspend fun getUserByEmail(newEmail: String) : User {
        return userDAO.getUserByEmail(newEmail)
    }

    @WorkerThread
    fun updateProfilePicture(imageOfProfile : Uri){
        userDAO.updateProfilePicture(imageOfProfile)
    }
}
