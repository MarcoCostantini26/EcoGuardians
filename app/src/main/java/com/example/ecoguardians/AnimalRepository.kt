package com.example.ecoguardians

import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.AnimalDAO

class AnimalRepository (private val animalDAO:AnimalDAO) {

    /*val allAnimals: List<ListAnimal> = animalDAO.getAllAnimals()

    //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
    //By default Room runs suspend queries off the main thread*/
    @WorkerThread
    suspend fun getFavoritesImage() {
        animalDAO.getFavoritesImages()
    }

    @WorkerThread
    suspend fun getFavoritesNames() : List<String>{
        return animalDAO.getFavoritesNames()
    }

    @WorkerThread
    suspend fun getName() : List<String>{
        return animalDAO.getName()
    }

    @WorkerThread
    suspend fun addFavoriteAnimal(name: String) {
        animalDAO.addFavoriteAnimal(name)
    }

    @WorkerThread
    suspend fun removeFavoriteAnimal(name: String) {
        animalDAO.removeFavoriteAnimal(name)
    }

    @WorkerThread
    suspend fun insertAnimal(item : Animal){
        animalDAO.insert(item)
    }
    @WorkerThread
    suspend fun getImage(name : String) : Int{
        return animalDAO.getImage(name)
    }

    @WorkerThread
    suspend fun getNumberSpecies(name : String) : String{
        return animalDAO.getNumberSpecies(name)
    }
    @WorkerThread
    suspend fun getClassification(name : String) : String{
        return animalDAO.getClassification(name)
    }
    @WorkerThread
    suspend fun getAverageLife(name : String) : String{
        return animalDAO.getAverageLife(name)
    }
    @WorkerThread
    suspend fun getPosition(name : String) : String{
        return animalDAO.getPosition(name)
    }
    @WorkerThread
    suspend fun getDescription(name: String): String{
        return animalDAO.getDescription(name)
    }
    @WorkerThread
    suspend fun getThreats(name: String): String{
        return animalDAO.getThreats(name)
    }
    @WorkerThread
    suspend fun getWhatYouCanDo(name: String): String{
        return animalDAO.getWhatYouCanDo(name)
    }
    @WorkerThread
    suspend fun getSeriousLink(name: String): String{
        return animalDAO.getSeriousLink(name)
    }
    @WorkerThread
    suspend fun getLatitude(name: String): Double{
        return animalDAO.getLatitude(name)
    }
    @WorkerThread
    suspend fun getLongitude(name: String): Double{
        return animalDAO.getLongitude(name)
    }

    @WorkerThread
    suspend fun isAnimalFavorite(name: String): Boolean{
        return animalDAO.isAnimalFavorite(name)
    }

}
