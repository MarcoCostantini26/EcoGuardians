package com.example.ecoguardians.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Animal::class, User::class], version = 3, exportSchema = true)
abstract class EcoGuardiansDatabase : RoomDatabase() {

    abstract fun AnimalDAO(): AnimalDAO
    abstract fun UserDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: EcoGuardiansDatabase ?= null

        fun getDatabase(context: Context): EcoGuardiansDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoGuardiansDatabase::class.java,
                    "eco_guardians_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance

                instance
            }
        }
    }

}