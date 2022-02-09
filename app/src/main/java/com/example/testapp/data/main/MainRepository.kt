package com.example.testapp.data.main

import com.example.testapp.domain.UserService

class MainRepository constructor(private val retrofitService: UserService) {
    suspend fun getAllInformation() = retrofitService.getUserInfo()
}
