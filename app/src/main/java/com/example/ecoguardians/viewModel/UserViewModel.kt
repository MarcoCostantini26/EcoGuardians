package com.example.ecoguardians.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel()  {

    fun getUsername() = viewModelScope.launch {
        repository.getUsername()
    }

    fun getPassword() = viewModelScope.launch {
        repository.getPassword()
    }

}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return UserViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
}
}