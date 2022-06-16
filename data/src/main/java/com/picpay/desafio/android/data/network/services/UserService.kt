package com.picpay.desafio.android.data.network.services

import com.picpay.desafio.android.data.models.user.response.UserModel
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers(): List<UserModel>
}