package com.testapp.testusernameapplication.repository.datasource

import com.testapp.testusernameapplication.entity.User

interface LocalUserRep {
    suspend fun getUserFromLocal(): User

    suspend fun saveUserToLocal(user: User)
}