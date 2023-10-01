package com.example.ecoguardians.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListAnimal::class], version = 1, exportSchema = true)
abstract class EcoGuardiansDatabase : RoomDatabase() {

    abstract fun AnimalDAO(): AnimalDAO

    companion object {
        @Volatile
        private var INSTANCE: EcoGuardiansDatabase ?= null

        fun getDatabase(context: Context): EcoGuardiansDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoGuardiansDatabase::class.java,
                    "eco_guardians_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }

}