package com.example.ecoguardians.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BadgeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Badge)

    @Query("SELECT isCompleted FROM Badge WHERE :email = email")
    fun isCompleted(email : String): Boolean

}