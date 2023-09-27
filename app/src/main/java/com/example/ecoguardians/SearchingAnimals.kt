package com.example.ecoguardians

import android.app.Application
import com.example.ecoguardians.data.EcoGuardiansDatabase

class SearchingAnimals : Application(){
    // lazy --> the database and the repository are only created when they're needed
    private val database by lazy { EcoGuardiansDatabase.getDatabase(this) }
    val repository by lazy { AnimalRepository(database.AnimalDAO()) }
}