package com.example.ecoguardians

import android.app.Application
import com.example.ecoguardians.data.EcoGuardiansDatabase

class EcoGuardiansApplication : Application(){
    private val database by lazy { EcoGuardiansDatabase.getDatabase(this) }
    val userRepository by lazy { UserRepository(database.UserDAO()) }
    val animalRepository by lazy { AnimalRepository(database.AnimalDAO()) }
}