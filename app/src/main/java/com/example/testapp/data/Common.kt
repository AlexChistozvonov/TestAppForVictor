package com.example.testapp.data

import com.example.testapp.domain.UserService


object Common {
    private val base_url = "https://api.github.com/"
    val retrofitService: UserService
        get() = RetrofitClient.getClient(base_url).create(UserService::class.java)
}