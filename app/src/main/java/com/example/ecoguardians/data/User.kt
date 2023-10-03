package com.example.ecoguardians.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val profilePicture: Int,
    val position: String,
    val isInSession: Boolean
)
