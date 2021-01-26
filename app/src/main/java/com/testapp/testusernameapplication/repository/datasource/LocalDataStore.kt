package com.testapp.testusernameapplication.repository.datasource

import com.testapp.testusernameapplication.dao.UserDao
import com.testapp.testusernameapplication.entity.User

class LocalDataStore(private val dao: UserDao): LocalUserRep {
    override suspend fun getUserFromLocal(): User {
        return dao.getUser()
    }

    override suspend fun saveUserToLocal(user: User) {
        dao.insertUser(user)
    }
}