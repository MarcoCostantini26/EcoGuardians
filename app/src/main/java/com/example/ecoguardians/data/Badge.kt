package com.example.ecoguardians.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Badge", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["email"], childColumns = ["email"])])
data class Badge (
    @PrimaryKey val id: Int,
    val isCompleted: Boolean,
    val description: String,
    val email: String
)