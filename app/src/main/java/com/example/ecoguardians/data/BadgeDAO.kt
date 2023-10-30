package com.example.ecoguardians.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface BadgeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Badge)

    @Query("SELECT * FROM Badge WHERE emailUser = :email")
    fun getBadgesForUser(email : String): List<Badge>

    @Update
    suspend fun updateBadge(badge: Badge)


    @Query("SELECT isCompleted FROM Badge WHERE :email = emailUser AND id = :id")
    fun isCompleted(email : String, id : Int): Boolean

    @Query("UPDATE Badge" +
            " SET isCompleted = 1" +
            " WHERE emailUser= :email AND id = :id")
    fun setCompletedTrue(email : String, id : Int)

    @Query("UPDATE Badge" +
            " SET emailUser = :email" +
            " WHERE id = :id")
    fun updateEmail(email : String, id : Int)

    @Query("UPDATE Badge" +
            " SET isCompleted = :isCompleted" +
            " WHERE id = :id AND emailUser = :email")
    fun updateEmail(email : String, id : Int, isCompleted : Boolean)
}