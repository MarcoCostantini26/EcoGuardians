package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.AnimalRepository
import com.example.ecoguardians.data.Animal
import kotlinx.coroutines.launch

class AnimalViewModel (private val repository: AnimalRepository): ViewModel() {

    //val allItems = repository.allAnimals

    fun addAnimal(item: Animal) = viewModelScope.launch {
        repository.insertAnimal(item)
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