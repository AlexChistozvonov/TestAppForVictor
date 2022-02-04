package com.example.testapp


object Common {
    private val base_url = "https://api.github.com/"
    val retrofitService: UserService
        get() = RetrofitClient.getClient(base_url).create(UserService::class.java)
}