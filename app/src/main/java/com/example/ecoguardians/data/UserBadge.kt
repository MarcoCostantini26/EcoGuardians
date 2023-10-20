package com.example.ecoguardians.data

import androidx.room.Embedded
import androidx.room.Relation

data class UserBadge (
    @Embedded val email : User,
    @Relation(
        parentColumn = "email",
        entityColumn = "email"
    )
    val badges: List<Badge>
)