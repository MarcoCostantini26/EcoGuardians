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
    suspend fun getFavoritesNames() {
        animalDAO.getFavoritesNames()
    }

    @WorkerThread
    suspend fun getName() : List<String>{
        return animalDAO.getName()
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

}
