package com.example.ecoguardians.data

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Animal")
data class Animal (
    @PrimaryKey
    val animal: String,
    val image: Int,
    val position: String,
    val numberSpecies: String,
    val classification: String,
    val averageLife: String,
    val animalDescription: String,
    val threats: String,
    val whatYouCanDo: String,
    val seriousLink: String,
    val latitude : Double,
    val longitude : Double,
    val favorite: Boolean
)