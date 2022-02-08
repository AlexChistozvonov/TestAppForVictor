package com.example.testapp

class MainRepository constructor(private val retrofitService: UserService) {
    suspend fun getAllInformation() = retrofitService.getUserInfo()
}