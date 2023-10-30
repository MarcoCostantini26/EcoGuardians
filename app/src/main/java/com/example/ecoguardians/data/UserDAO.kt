package com.example.ecoguardians.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDAO {
    @Transaction
    @Query("SELECT * FROM User WHERE email= :email")
    fun getUserBadges(email : String): UserBadge

    @Query("SELECT email FROM User WHERE isInSession = 1")
    fun getEmail(): String

    @Query("SELECT username FROM User WHERE isInSession = 1")
    fun getUsername(): String

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

    @Query("SELECT isInSession FROM User WHERE isInSession=1 and email = :email")
    fun userInSession(email: String): Boolean

    @Query("UPDATE User" +
            " SET isInSession = 1" +
            " WHERE email= :email")
    fun setSessionTrue(email: String)

    @Query("UPDATE User" +
            " SET isInSession = 0" +
            " WHERE email= :email")
    fun setSessionFalse(email: String)

    @Query("UPDATE Animal SET email = :newEmail")
    suspend fun updateEmail(newEmail: String)

    // query per ottenere l'utente in base all'email
    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): User

    // calcolo quanti user sono registrati
    @Query("SELECT COUNT(*) FROM User")
    suspend fun countUsers(): Int

}