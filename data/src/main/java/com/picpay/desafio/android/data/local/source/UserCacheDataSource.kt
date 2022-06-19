package com.picpay.desafio.android.data.local.source

import com.picpay.desafio.android.data.local.database.PicPayDatabase
import com.picpay.desafio.android.data.local.database.entities.UserEntity

interface UserCacheDataSource {
    suspend fun insertUser(user: UserEntity): Long
    suspend fun getAllUsers(
    ): List<UserEntity>?
    suspend fun getUserByUsername(
        username: String,
    ): UserEntity?
    suspend fun deleteUser(username: String): Int
}

class UserCacheDataSourceImp(private val database: PicPayDatabase) : UserCacheDataSource {
    override suspend fun insertUser(user: UserEntity): Long {
      return database.userDao().insertUser(user)
    }

    override suspend fun getAllUsers(): List<UserEntity>? {
        return database.userDao().getAllUsers()
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
      return database.userDao().getUserByUsername(username)
    }

    override suspend fun deleteUser(username: String): Int {
       return database.userDao().deleteUser(username)
    }

}