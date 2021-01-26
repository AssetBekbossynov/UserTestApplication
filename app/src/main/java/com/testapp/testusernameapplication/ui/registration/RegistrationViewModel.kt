package com.testapp.testusernameapplication.ui.registration

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import com.testapp.testusernameapplication.entity.User
import com.testapp.testusernameapplication.repository.UserRep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userRepository: UserRep) : ViewModel() {

    fun registerUser(username: String, firstName: String, secondName: String, email: String,
                     password: String, phone: String, status: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.storeUserToRemote(
                User(
                    username = username,
                    firstName = firstName,
                    lastName = secondName,
                    email = email,
                    password = password,
                    phone = phone,
                    userStatus = status
                )
            )
        }
    }

}