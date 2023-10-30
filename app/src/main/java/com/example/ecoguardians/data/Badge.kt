package com.example.ecoguardians.data

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Badge", primaryKeys = ["id","emailUser"])
data class Badge (
    val id: Int,
    var isCompleted: Boolean,
    val description: String,
    val emailUser: String
)