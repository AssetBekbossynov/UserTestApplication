package com.testapp.testusernameapplication.repository.datasource

import com.testapp.testusernameapplication.entity.User
import okhttp3.ResponseBody

interface RemoteUserRep {
    suspend fun storeUserToRemote(user: User): ResponseBody

    suspend fun getUserFromRemote(username: String): User
}