package com.example.testapp.data.details

import com.example.testapp.domain.UserService

class DetailsRepository constructor(private val retrofitService: UserService) {
    suspend fun getOneUserInformation(id: String) = retrofitService.getUser(id)
}

