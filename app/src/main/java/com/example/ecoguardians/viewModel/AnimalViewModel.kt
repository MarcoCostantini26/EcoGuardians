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

    fun addFavoriteAnimal(name: String) = viewModelScope.launch {
        repository.addFavoriteAnimal(name)
    }

    fun removeFavoriteAnimal(name: String) = viewModelScope.launch {
        repository.removeFavoriteAnimal(name)
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

    suspend fun getFavoritesNames() : List<String> {
        return withContext(Dispatchers.IO){
            repository.getFavoritesNames()
        }
    }

    suspend fun getFavoritesImage() {
        return withContext(Dispatchers.IO){
            repository.getFavoritesImage()
        }
    }
    
    suspend fun getDescription(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getDescription(name)
        }
    }

    suspend fun getThreats(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getThreats(name)
        }
    }

    suspend fun getWhatYouCanDo(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getWhatYouCanDo(name)
        }
    }

    suspend fun getSeriousLink(name : String) : String{
        return withContext(Dispatchers.IO){
            repository.getSeriousLink(name)
        }
    }

    suspend fun getLatitude(name : String) : Double{
        return withContext(Dispatchers.IO){
            repository.getLatitude(name)
        }
    }

    suspend fun getLongitude(name : String) : Double{
        return withContext(Dispatchers.IO){
            repository.getLongitude(name)
        }
    }

    suspend fun isAnimalFavorite(name : String) : Boolean{
        return withContext(Dispatchers.IO){
            repository.isAnimalFavorite(name)
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