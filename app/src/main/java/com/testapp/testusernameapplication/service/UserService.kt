package com.testapp.testusernameapplication.service

import com.testapp.testusernameapplication.entity.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("user/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @POST("user")
    suspend fun uploadUser(@Body user: User): ResponseBody
}