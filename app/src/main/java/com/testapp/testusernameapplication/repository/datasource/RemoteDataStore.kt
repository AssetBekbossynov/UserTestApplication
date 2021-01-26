package com.testapp.testusernameapplication.repository.datasource

import com.testapp.testusernameapplication.entity.User
import com.testapp.testusernameapplication.service.UserService
import okhttp3.ResponseBody

class RemoteDataStore (private val service: UserService): RemoteUserRep {

    override suspend fun storeUserToRemote(user: User): ResponseBody {
        return service.uploadUser(user)
    }

    override suspend fun getUserFromRemote(username: String): User {
        return service.getUser(username)
    }
}