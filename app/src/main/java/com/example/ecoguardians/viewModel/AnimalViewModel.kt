package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.AnimalRepository
import com.example.ecoguardians.data.Animal
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalViewModel (private val repository: AnimalRepository): ViewModel() {

    //val allItems = repository.allAnimals

    fun addAnimal(item: Animal) = viewModelScope.launch {
        repository.insertAnimal(item)
    }

    suspend fun getName() : List<String> {
        return withContext(Dispatchers.IO) {
            repository.getName()
        }
    }

    suspend fun getImage(name : String) : Int{
        return withContext(Dispatchers.IO){
            repository.getImage(name)
        }
    }

    suspend fun getNumberSpecies(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getNumberSpecies(name)
        }
    }

    suspend fun getClassification(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getClassification(name)
        }
    }

    suspend fun getAverageLife(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getAverageLife(name)
        }
    }

    suspend fun getPosition(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getPosition(name)
        }
    }
}

class AnimalViewModelFactory(private val repository: AnimalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}