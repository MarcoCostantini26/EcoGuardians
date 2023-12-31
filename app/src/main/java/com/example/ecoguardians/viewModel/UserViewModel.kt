package com.example.ecoguardians.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.AnimalRepository
import com.example.ecoguardians.UserRepository
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.User
import com.example.ecoguardians.data.UserAnimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserRepository, private val animalRepository: AnimalRepository) : ViewModel() {

    fun addAnimal(item: Animal) = viewModelScope.launch {
        animalRepository.insertAnimal(item)
    }

    suspend fun isAnimalFavorite(name: String, email: String): Boolean {
        return withContext(Dispatchers.IO) {
            animalRepository.isAnimalFavorite(name, email)
        }
    }

    fun addUser(item: User) = viewModelScope.launch {
        repository.insertUser(item)
    }

    suspend fun getUsername(): String {
        return withContext(Dispatchers.IO) {
            repository.getUsername()
        }
    }

    suspend fun doesUserExists(email: String): Int {
        return withContext(Dispatchers.IO) {
            repository.doesUserExist(email)
        }
    }

    suspend fun isPasswordCorrect(email: String, password: String): Int {
        return withContext(Dispatchers.IO) {
            repository.isPasswordCorrect(email, password)
        }
    }

    fun setSessionTrue(email: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.setSessionTrue(email)
        }
    }

    fun setSessionFalse(email: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.setSessionFalse(email)
        }
    }

    fun notificationOn(email: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.notificationOn(email)
        }
    }

    fun notificationOff(email: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.notificationOff(email)
        }
    }

    suspend fun notificationEnabled(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.notificationEnabled(email)
        }
    }

    fun updateProfilePicture(imageOfProfile: Uri) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.updateProfilePicture(imageOfProfile)
        }
    }

    suspend fun countUserInSession(): Int {
        return withContext(Dispatchers.IO) {
            repository.countUserInSession()
        }
    }

    suspend fun getEmail(): String {
        return withContext(Dispatchers.IO) {
            repository.getEmail()
        }
    }

    suspend fun getUserWithAnimals(email: String): UserAnimal {
        val user = repository.getUserByEmail(email)
        val animals = animalRepository.getAnimalsByUser(email)
        return UserAnimal(user, animals)
    }

    suspend fun getProfilePicture(): Uri {
        return withContext(Dispatchers.IO) {
            repository.getProfilePicture()
        }
    }

}

class UserViewModelFactory(private val repository: UserRepository, private val animalRepository: AnimalRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository, animalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}