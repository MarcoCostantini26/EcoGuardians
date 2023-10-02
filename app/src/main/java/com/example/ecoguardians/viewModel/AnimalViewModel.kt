package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import com.example.ecoguardians.AnimalRepository

class AnimalViewModel (private val repository: AnimalRepository): ViewModel() {

    /*val allItems = repository.allAnimals

    fun addItem(item: ListAnimal) = viewModelScope.launch {
        repository.insertAnimal(item)
    }

    fun deleteItem(item: ListAnimal) = viewModelScope.launch {
        repository.deleteAnimal(item)
    }

    fun clearItems() = viewModelScope.launch {
        repository.deleteAllAnimals()
    }

}

class ItemsViewModelFactory(private val repository: AnimalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/
}