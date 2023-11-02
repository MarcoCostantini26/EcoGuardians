package com.example.ecoguardians

import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.data.BadgeDAO

class BadgeRepository (private val badgeDAO: BadgeDAO) {
    @WorkerThread
    suspend fun insertBadge(item : Badge){
        badgeDAO.insert(item)
    }

    @WorkerThread
    suspend fun updateBadge(item : Badge){
        badgeDAO.updateBadge(item)
    }

    @WorkerThread
    suspend fun getBadgesForUser(email : String) : List<Badge>{
        return badgeDAO.getBadgesForUser(email)
    }

    @WorkerThread
    suspend fun isCompleted(email : String, id : Int) : Boolean{
        return badgeDAO.isCompleted(email, id)
    }

    @WorkerThread
    fun firstComplete(id : Int, email : String) : Boolean{
        return badgeDAO.firstComplete(id, email)
    }

    fun setFirstComplete(id : Int, email : String) {
        badgeDAO.setFirstComplete(id, email)
    }

    fun getDescription(id : Int) : String{
        return badgeDAO.getDescription(id)
    }

    @WorkerThread
    suspend fun setCompletedTrue(email : String, id : Int){
        badgeDAO.setCompletedTrue(email, id)
    }

    @WorkerThread
    suspend fun updateEmail(email : String, id : Int){
        badgeDAO.updateEmail(email, id)
    }

    @WorkerThread
    suspend fun updateIsCompleted(email : String, id : Int, isCompleted : Boolean){
        badgeDAO.updateEmail(email, id, isCompleted)
    }
}
