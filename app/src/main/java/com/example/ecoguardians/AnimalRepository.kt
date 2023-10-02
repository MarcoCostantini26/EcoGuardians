package com.example.ecoguardians

import com.example.ecoguardians.data.AnimalDAO

class AnimalRepository (private val animalDAO:AnimalDAO) {

    /*val allAnimals: List<ListAnimal> = animalDAO.getAllAnimals()

    //@WorkerThread Denotes that the annotated method should only be called on a worker thread.
    //By default Room runs suspend queries off the main thread
    @WorkerThread
    suspend fun insertAnimal(item: ListAnimal) {
        animalDAO.insert(item)
    }

    @WorkerThread
    suspend fun deleteAnimal(item: ListAnimal) {
        animalDAO.delete(item)
    }

    @WorkerThread
    suspend fun deleteAllAnimals() {
        animalDAO.deleteAll()
    }*/
}
