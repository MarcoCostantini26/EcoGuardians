package com.example.ecoguardians.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT email FROM User WHERE isInSession = 1")
    fun getEmail(): String

    @Query("SELECT password FROM User WHERE isInSession = 1")
    fun getPassword(): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: User)

    @Query("SELECT COUNT(*) FROM User WHERE email= :email")
    fun doesUserExist(email: String) : Int

}