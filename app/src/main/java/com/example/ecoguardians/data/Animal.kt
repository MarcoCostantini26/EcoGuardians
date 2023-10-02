package com.example.ecoguardians.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Animal")
data class Animal (
    @PrimaryKey val animal: String,
    val image: Int,
    val position: String,
    val numberSpecies: Int,
    val classification: String,
    val averageLife: Int,
    val animalDescription: String,
    val threats: String,
    val whatYouCanDo: String,
    val seriousLink: String,
    val favorite: Boolean
)