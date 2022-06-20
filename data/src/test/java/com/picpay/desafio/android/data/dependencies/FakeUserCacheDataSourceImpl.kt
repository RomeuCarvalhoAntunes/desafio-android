package com.picpay.desafio.android.data.dependencies

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.local.source.UserCacheDataSource

class FakeUserCacheDataSourceImpl(private val fakeDatabase: HashMap<String, UserEntity>?) :
    UserCacheDataSource {
    override suspend fun insertUser(user: UserEntity): Long {
        return if (fakeDatabase != null) {
            fakeDatabase[user.username] = user
            1
        } else {
            -1
        }
    }

    override suspend fun getAllUsers(): List<UserEntity>? {
        return fakeDatabase?.values?.toList()
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return fakeDatabase?.get(username)
    }

    override suspend fun deleteUser(username: String): Int {
        return if (fakeDatabase != null) {
            fakeDatabase.remove(username)
            1
        } else {
            -1
        }
    }
}