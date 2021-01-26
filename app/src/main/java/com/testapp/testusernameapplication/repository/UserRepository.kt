package com.testapp.testusernameapplication.repository

import com.testapp.testusernameapplication.entity.User
import com.testapp.testusernameapplication.repository.datasource.LocalUserRep
import com.testapp.testusernameapplication.repository.datasource.RemoteUserRep
import okhttp3.ResponseBody

class UserRepository (private val remoteStore: RemoteUserRep, private val localStore: LocalUserRep): UserRep {

    override suspend fun storeUserToRemote(user: User): ResponseBody {
        return remoteStore.storeUserToRemote(user)
    }

    override suspend fun getUserFromRemote(username: String): User {
        return remoteStore.getUserFromRemote(username)
    }

    override suspend fun storeUserToLocal(user: User) {
        return localStore.saveUserToLocal(user)
    }

    override suspend fun getUserFromLocal(): User {
        return localStore.getUserFromLocal()
    }
}