package com.example.ecoguardians.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Animal::class, User::class, Badge::class], version = 13, exportSchema = true)
@TypeConverters(Converter::class)
abstract class EcoGuardiansDatabase : RoomDatabase() {

    abstract fun AnimalDAO(): AnimalDAO
    abstract fun UserDAO(): UserDAO

    abstract fun BadgeDAO(): BadgeDAO

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