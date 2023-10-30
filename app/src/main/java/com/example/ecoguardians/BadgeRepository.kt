package com.example.ecoguardians

import androidx.annotation.WorkerThread
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.data.BadgeDAO

class BadgeRepository (private val badgeDAO: BadgeDAO) {
    @WorkerThread
    suspend fun insertBadge(item : Badge){
        badgeDAO.insert(item)
    }

    @WorkerThread
    fun isCompleted(email : String) : Boolean{
        return badgeDAO.isCompleted(email)
    }

}
