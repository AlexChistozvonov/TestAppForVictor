package com.example.testapp

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users?since=0")
    fun getUserInfo(): Call<MutableList<UserModel>>
}