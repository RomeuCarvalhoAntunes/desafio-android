package com.picpay.desafio.android.data.local.source

import com.picpay.desafio.android.data.local.database.PicPayDatabase
import com.picpay.desafio.android.data.local.database.entities.UserEntity

interface UserCacheDataSource {
    suspend fun getUsers(): List<UserEntity>
    suspend fun getUserByUsername(username: String): UserEntity
    suspend fun insertUser(user: UserEntity): Long
    suspend fun deleteAllUsers()
}

class UserCacheDataSourceImp(private val database: PicPayDatabase) : UserCacheDataSource {
    override suspend fun getUsers(): List<UserEntity> {
        return database.userDao().getUsers()
    }

    override suspend fun getUserByUsername(username: String): UserEntity {
        return database.userDao().getUserByUsername(username)
    }

    override suspend fun insertUser(user: UserEntity): Long {
        return database.userDao().insertUser(user)
    }

    override suspend fun deleteAllUsers() {
        return database.userDao().deleteAll()
    }
}