package com.example.testapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users?since=0")
    fun getUserInfo(): Call<MutableList<UserModel>>

    @GET("user/{id}")
    fun getUser(@Path("id") id: String): Call<UserModel>
}