package com.testapp.testusernameapplication.ui.userInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testapp.testusernameapplication.entity.User
import com.testapp.testusernameapplication.repository.UserRep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInformationViewModel(private val userRepository: UserRep): ViewModel() {

    val userInfo: MutableLiveData<User> = MutableLiveData()

    fun getUserFromRemote(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getUserFromRemote(username)
            saveUserToLocal(user)
        }
    }

    private fun saveUserToLocal(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.storeUserToLocal(user)
            getUserFromLocal()
        }
    }

    private fun getUserFromLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getUserFromLocal()
            withContext(Dispatchers.Main) {
                userInfo.value = user
            }
        }
    }
}