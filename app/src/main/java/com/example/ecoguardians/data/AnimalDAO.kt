package com.example.ecoguardians.data

import androidx.room.*

@Dao
interface AnimalDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Animal)

    @Query("SELECT image FROM Animal WHERE :name = animal")
    fun getImage(name : String): Int

    @Query("SELECT numberSpecies FROM Animal WHERE :name = animal")
    fun getNumberSpecies(name : String): String

    @Query("SELECT classification FROM Animal WHERE :name = animal")
    fun getClassification(name : String): String

    @Query("SELECT averageLife FROM Animal WHERE :name = animal")
    fun getAverageLife(name : String): String

    @Query("SELECT position FROM Animal WHERE :name = animal")
    fun getPosition(name : String): String

    @Query("SELECT animalDescription FROM Animal WHERE :name = animal")
    fun getDescription(name : String): String

    @Query("SELECT threats FROM Animal WHERE :name = animal")
    fun getThreats(name : String): String

    @Query("SELECT whatYouCanDo FROM Animal WHERE :name = animal")
    fun getWhatYouCanDo(name : String): String

    @Query("SELECT seriousLink FROM Animal WHERE :name = animal")
    fun getSeriousLink(name : String): String

    @Query("SELECT latitude FROM Animal WHERE :name = animal")
    fun getLatitude(name : String): Double

    @Query("SELECT longitude FROM Animal WHERE :name = animal")
    fun getLongitude(name : String): Double

    @Query("SELECT animal FROM Animal")
    suspend fun getName(): List<String>

    @Query("UPDATE Animal SET favorite = 1 WHERE :name = animal AND :email = email")
    suspend fun addFavoriteAnimal(name: String, email: String)

    @Query("UPDATE Animal SET favorite = 0 WHERE :name = animal AND :email = email")
    suspend fun removeFavoriteAnimal(name: String, email: String)

    @Query("UPDATE Animal SET favorite = :fav WHERE :name = animal")
    suspend fun updateFavorite(name: String, fav: Boolean)

    @Query("UPDATE Animal SET isVisited = 1 WHERE :name = animal and email = :email")
    suspend fun setIsVisitedTrue(name: String, email: String)

    @Query("SELECT animal FROM Animal WHERE favorite = 1 AND email =:email")
    fun getFavoritesNames(email: String): List<String>

    @Query("SELECT image FROM Animal WHERE favorite = 1")
    fun getFavoritesImages(): List<Int>

    @Query("SELECT favorite FROM Animal WHERE animal = :animalName AND email = :email")
    suspend fun isAnimalFavorite(animalName: String, email: String): Boolean

    @Query("SELECT animal FROM Animal WHERE email = :email AND favorite = 1")
    suspend fun getFavoritesByEmail(email: String): List<String>

    @Query("SELECT animal FROM Animal WHERE email = :email")
    suspend fun getAnimalInSession(email: String): List<String>

    @Query("SELECT isVisited FROM Animal WHERE animal = :name and email = :email")
    suspend fun isVisited(name: String, email : String): Boolean

    @Query("SELECT * FROM Animal WHERE email = :userEmail")
    suspend fun getAnimalsByUser(userEmail: String): List<Animal>

    @Query("SELECT COUNT(*) FROM Animal WHERE isVisited = 1  and email = :email")
    suspend fun countIsVisited(email : String): Int
}
