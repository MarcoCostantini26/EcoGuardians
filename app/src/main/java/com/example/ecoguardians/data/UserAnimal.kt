package com.example.ecoguardians.data

import androidx.room.Embedded
import androidx.room.Relation

data class UserAnimal (
    @Embedded val email : User,
    @Relation(
        parentColumn = "email",
        entityColumn = "email"
    )
    val animals:List<Animal>
)