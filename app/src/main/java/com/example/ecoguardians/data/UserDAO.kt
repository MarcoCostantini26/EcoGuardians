package com.example.ecoguardians.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT email FROM User WHERE isInSession = 1")
    fun getEmail(): String
}