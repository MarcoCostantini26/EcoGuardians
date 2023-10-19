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

    @Query("SELECT COUNT(*) FROM User WHERE email= :email and password= :password")
    fun isPasswordCorrect(email: String, password: String) : Int

    @Query("SELECT COUNT(*) FROM User WHERE isInSession=1")
    fun countUserInSession(): Int

    @Query("UPDATE User" +
            " SET isInSession = 1" +
            " WHERE email= :email")
    fun setSessionTrue(email: String)

    @Query("UPDATE User" +
            " SET isInSession = 0" +
            " WHERE email= :email")
    fun setSessionFalse(email: String)
}