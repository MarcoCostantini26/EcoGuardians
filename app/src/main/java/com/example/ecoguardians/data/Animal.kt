package com.example.ecoguardians.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "Animal", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["email"],
        childColumns = ["email"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["animal", "email"],
    indices = [Index("email")]
)

data class Animal (
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
    val favorite: Boolean,
    val isVisited: Boolean,
    val email: String
)
