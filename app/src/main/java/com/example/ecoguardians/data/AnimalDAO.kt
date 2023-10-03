package com.example.ecoguardians.data

import androidx.room.*

@Dao
interface AnimalDAO {
    //@Query("SELECT * FROM list_animals WHERE animal IN (:animal)")
    //fun getAnimal(animal: String): String

/*    @Query("SELECT * FROM list_animals")
    fun getAllAnimals(): List<ListAnimal>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Animal)

    /*@Delete
    suspend fun delete(item: ListAnimal)*/

    /*@Query("DELETE FROM list_animals")
    suspend fun deleteAll()*/

    @Query("SELECT image FROM Animal WHERE :name = animal")
    fun getImage(name : String): Int

    @Query("SELECT animal FROM Animal")
    fun getName(): String

    @Query("SELECT animal FROM Animal WHERE favorite = 1")
    fun getFavoritesNames(): List<String>

    @Query("SELECT image FROM Animal WHERE favorite = 1")
    fun getFavoritesImages(): List<Int>
}
