package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.AnimalRepository
import com.example.ecoguardians.BadgeRepository
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.data.UserBadge
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BadgeViewModel (private val repository: BadgeRepository): ViewModel() {

    fun addBadge(item: Badge) = viewModelScope.launch {
        repository.insertBadge(item)
    }

    fun updateBadge(item: Badge) = viewModelScope.launch {
        repository.updateBadge(item)
    }

    suspend fun getBadgesForUser(email : String) : List<Badge> {
        return withContext(Dispatchers.IO) {
            repository.getBadgesForUser(email)
        }
    }

    suspend fun isCompleted(email : String, id : Int) : Boolean{
        return withContext(Dispatchers.IO){
            repository.isCompleted(email, id)
        }
    }

    suspend fun firstComplete(id : Int, email: String) : Boolean{
        return withContext(Dispatchers.IO){
            repository.firstComplete(id, email)
        }
    }

    suspend fun setFirstComplete(id : Int, email: String) {
        withContext(Dispatchers.IO){
            repository.setFirstComplete(id, email)
        }
    }

    suspend fun getDescription(id : Int) : String {
        return withContext(Dispatchers.IO) {
            repository.getDescription(id)
        }
    }

    fun setCompletedTrue(email: String, id : Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.setCompletedTrue(email, id)
        }
    }

    fun updateEmail(email: String, id : Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.updateEmail(email, id)
        }
    }

    fun updateIsCompleted(email: String, id : Int, isCompleted : Boolean) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.updateIsCompleted(email, id, isCompleted)
        }
    }

}

class BadgeViewModelFactory(private val repository: BadgeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BadgeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BadgeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}