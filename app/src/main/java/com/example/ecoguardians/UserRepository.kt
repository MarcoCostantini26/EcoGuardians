package com.example.ecoguardians

import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.User
import com.example.ecoguardians.data.UserDAO

class UserRepository(private val userDAO: UserDAO) {

   //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
   //By default Room runs suspend queries off the main thread
   @WorkerThread
   suspend fun getUsername() {
       userDAO.getEmail()
   }

   @WorkerThread
   suspend fun getPassword() {
       userDAO.getPassword()
   }

    @WorkerThread
    suspend fun insertUser(item : User){
        userDAO.insert(item)
    }

}