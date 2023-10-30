package com.example.ecoguardians

import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.AnimalDAO

class AnimalRepository (private val animalDAO:AnimalDAO) {

    //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
    //By default Room runs suspend queries off the main thread*/
    @WorkerThread
    fun getFavoritesNames(email: String) : List<String>{
        return animalDAO.getFavoritesNames(email)
    }

    @WorkerThread
    suspend fun getName() : List<String>{
        return animalDAO.getName()
    }

    @WorkerThread
    suspend fun addFavoriteAnimal(name: String, email: String) {
        animalDAO.addFavoriteAnimal(name, email)
    }

    @WorkerThread
    suspend fun isVisited(animalName: String) : Boolean{
        return animalDAO.isVisited(animalName)
    }

    @WorkerThread
    suspend fun removeFavoriteAnimal(name: String, email: String) {
        animalDAO.removeFavoriteAnimal(name, email)
    }

    @WorkerThread
    suspend fun insertAnimal(item : Animal){
        animalDAO.insert(item)
    }
    @WorkerThread
    fun getImage(name : String) : Int{
        return animalDAO.getImage(name)
    }

    @WorkerThread
    fun getNumberSpecies(name : String) : String{
        return animalDAO.getNumberSpecies(name)
    }
    @WorkerThread
    fun getClassification(name : String) : String{
        return animalDAO.getClassification(name)
    }
    @WorkerThread
    fun getAverageLife(name : String) : String{
        return animalDAO.getAverageLife(name)
    }
    @WorkerThread
    fun getPosition(name : String) : String{
        return animalDAO.getPosition(name)
    }
    @WorkerThread
    fun getDescription(name: String): String{
        return animalDAO.getDescription(name)
    }
    @WorkerThread
    fun getThreats(name: String): String{
        return animalDAO.getThreats(name)
    }
    @WorkerThread
    fun getWhatYouCanDo(name: String): String{
        return animalDAO.getWhatYouCanDo(name)
    }
    @WorkerThread
    fun getSeriousLink(name: String): String{
        return animalDAO.getSeriousLink(name)
    }
    @WorkerThread
    fun getLatitude(name: String): Double{
        return animalDAO.getLatitude(name)
    }
    @WorkerThread
    fun getLongitude(name: String): Double{
        return animalDAO.getLongitude(name)
    }

    @WorkerThread
    suspend fun isAnimalFavorite(name: String, email: String): Boolean{
        return animalDAO.isAnimalFavorite(name, email)
    }

    @WorkerThread
    suspend fun getAnimalsByUser(email: String) : List<Animal>{
        return animalDAO.getAnimalsByUser(email)
    }

}
