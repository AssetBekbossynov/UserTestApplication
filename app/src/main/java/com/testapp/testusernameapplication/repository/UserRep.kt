package com.testapp.testusernameapplication.repository

import com.testapp.testusernameapplication.entity.User
import okhttp3.ResponseBody

interface UserRep {

    suspend fun storeUserToRemote(user: User): ResponseBody

    suspend fun getUserFromRemote(username: String): User

    suspend fun storeUserToLocal(user: User)

    suspend fun getUserFromLocal(): User
}