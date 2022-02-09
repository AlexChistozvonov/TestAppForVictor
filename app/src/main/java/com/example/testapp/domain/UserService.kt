package com.example.testapp.domain

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users?since=0")
    suspend fun getUserInfo(): Response<MutableList<UserModel>>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: String): Response<UserModel>
}

