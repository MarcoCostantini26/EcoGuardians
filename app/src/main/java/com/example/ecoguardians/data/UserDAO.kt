package com.example.ecoguardians.data

import android.net.Uri
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDAO {
    @Transaction
    @Query("SELECT * FROM User WHERE email = :email")
    fun getUserBadges(email : String): List<UserBadge>

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    // Effettua l'aggiornamento nella tabella Badge
    @Query("UPDATE Badge SET isCompleted = :isCompleted WHERE id = :badgeId")
    suspend fun updateBadge(badgeId: Int, isCompleted: Boolean)

    @Query("SELECT email FROM User WHERE isInSession = 1")
    fun getEmail(): String

    @Query("SELECT username FROM User WHERE isInSession = 1")
    fun getUsername(): String

    @Query("SELECT profilePicture FROM User WHERE isInSession = 1")
    fun getProfilePicture(): Uri

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

    @Query("UPDATE User" +
            " SET notification = 1" +
            " WHERE email= :email")
    fun notificationOn(email: String)

    @Query("UPDATE User" +
            " SET notification = 0" +
            " WHERE email= :email")
    fun notificationOff(email: String)

    @Query("SELECT notification FROM User WHERE email = :email")
    suspend fun notificationEnabled(email: String): Boolean

    @Query("UPDATE Animal SET email = :newEmail")
    suspend fun updateEmail(newEmail: String)

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): User

    @Query("SELECT COUNT(*) FROM User")
    suspend fun countUsers(): Int

    @Query("UPDATE User" +
            " SET profilePicture = :imageOfProfile" +
            " WHERE isInSession = 1")
    fun updateProfilePicture(imageOfProfile: Uri)
}