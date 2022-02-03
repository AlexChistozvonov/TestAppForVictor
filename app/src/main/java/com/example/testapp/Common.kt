package com.example.testapp


object Common {
    private val BASE_URL = "https://api.github.com/"
    val retrofitService: UserService
        get() = RetrofitClient.getClient(BASE_URL).create(UserService::class.java)
}