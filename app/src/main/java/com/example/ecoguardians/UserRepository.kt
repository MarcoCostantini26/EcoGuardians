package com.example.ecoguardians

import android.net.Uri
import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.User
import com.example.ecoguardians.data.UserDAO

class UserRepository(private val userDAO: UserDAO) {

   //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
   //By default Room runs suspend queries off the main thread
   @WorkerThread
   suspend fun getUsername() : String {
       return userDAO.getUsername()
   }
    @WorkerThread
    suspend fun getEmail() : String{
        return userDAO.getEmail()
    }

    @WorkerThread
    suspend fun getProfilePicture() : Uri{
        return userDAO.getProfilePicture()
    }

   @WorkerThread
   suspend fun getPassword() {
       userDAO.getPassword()
   }

    @WorkerThread
    suspend fun insertUser(item : User){
        userDAO.insert(item)
    }

    @WorkerThread
    suspend fun doesUserExist(email : String) : Int{
        return userDAO.doesUserExist(email)
    }

    @WorkerThread
    suspend fun isPasswordCorrect(email : String, password: String) : Int{
        return userDAO.isPasswordCorrect(email, password)
    }

    suspend fun countUserInSession() : Int{
        return userDAO.countUserInSession()
    }
    @WorkerThread
    suspend fun setSessionTrue(email : String){
        userDAO.setSessionTrue(email)
    }
    @WorkerThread
    suspend fun setSessionFalse(email : String){
        userDAO.setSessionFalse(email)
    }
    @WorkerThread
    suspend fun updateProfilePicture(imageOfProfile : Uri){
        userDAO.updateProfilePicture(imageOfProfile)
    }
}