package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.BadgeRepository
import com.example.ecoguardians.data.Badge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BadgeViewModel (private val repository: BadgeRepository): ViewModel() {

    fun addBadge(item: Badge) = viewModelScope.launch {
        repository.insertBadge(item)
    }

    suspend fun isCompleted(email : String) : Boolean{
        return withContext(Dispatchers.IO){
            repository.isCompleted(email)
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