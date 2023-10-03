package com.example.ecoguardians.data

import androidx.room.*

@Dao
interface AnimalDAO {
    //@Query("SELECT * FROM list_animals WHERE animal IN (:animal)")
    //fun getAnimal(animal: String): String

/*    @Query("SELECT * FROM list_animals")
    fun getAllAnimals(): List<ListAnimal>*/

/*    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ListAnimal)*/

    /*@Delete
    suspend fun delete(item: ListAnimal)*/

    /*@Query("DELETE FROM list_animals")
    suspend fun deleteAll()*/
}
