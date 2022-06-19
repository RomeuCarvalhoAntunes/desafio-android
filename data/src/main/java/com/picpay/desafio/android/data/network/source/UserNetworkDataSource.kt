package com.picpay.desafio.android.data.network.source

import com.picpay.desafio.android.data.network.models.UserModel
import com.picpay.desafio.android.data.network.services.UserService
import javax.inject.Inject

interface UserNetworkDataSource {
    suspend fun getUsers(): List<UserModel>?
}

class UserNetworkDataSourceImpl @Inject constructor(private val service : UserService) : UserNetworkDataSource {
    override suspend fun getUsers(): List<UserModel>? {
        return service.getUsers()
    }
}