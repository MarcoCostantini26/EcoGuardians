package com.example.ecoguardians.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val username: String,
    val isInSession: Boolean,
    val notification: Boolean,
    val profilePicture: Uri
)


