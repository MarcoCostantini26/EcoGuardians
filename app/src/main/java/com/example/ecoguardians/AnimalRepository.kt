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

}
