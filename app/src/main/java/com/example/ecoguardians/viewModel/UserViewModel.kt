package com.example.ecoguardians.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecoguardians.UserRepository
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.User
import com.example.ecoguardians.data.UserBadge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserRepository) : ViewModel()  {

    fun addUser(item: User) = viewModelScope.launch {
        repository.insertUser(item)
    }

    suspend fun getUsername() : String {
        return withContext(Dispatchers.IO) {
            repository.getUsername()
        }
    }

    suspend fun getUserBadges(email : String) : List<UserBadge> {
        return withContext(Dispatchers.IO) {
            repository.getUserBadges(email)
        }
    }

    suspend fun getUsers() : List<User> {
        return withContext(Dispatchers.IO) {
            repository.getUsers()
        }
    }

    fun getPassword() = viewModelScope.launch {
        repository.getPassword()
    }

    suspend fun doesUserExists(email: String) : Int {
        return withContext(Dispatchers.IO) {
            repository.doesUserExist(email)
        }
    }

    suspend fun isPasswordCorrect(email: String, password: String) : Int {
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
        repository.setSessionFalse(email)
    }

    fun updateProfilePicture(imageOfProfile: Uri) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            repository.updateProfilePicture(imageOfProfile)
        }

    }

    suspend fun countUserInSession():Int{
        return withContext(Dispatchers.IO) {
            repository.countUserInSession()
        }
    }

    suspend fun getEmail(): String{
        return withContext(Dispatchers.IO) {
            repository.getEmail()
        }
    }

    suspend fun getProfilePicture(): Uri {
        return withContext(Dispatchers.IO) {
            repository.getProfilePicture()
        }
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