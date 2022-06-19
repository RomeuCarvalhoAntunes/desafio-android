package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.local.source.UserCacheDataSource
import com.picpay.desafio.android.data.mappers.UserMap
import com.picpay.desafio.android.data.network.models.UserModel
import com.picpay.desafio.android.data.network.source.UserNetworkDataSource
import com.picpay.desafio.android.data.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

interface UserRepository {
    suspend fun getUsers(): Flow<DataState<List<UserEntity>>>
    suspend fun getUsersDB(): List<UserEntity>
    suspend fun saveUsers(user: UserModel): Long
    suspend fun getUserByUsername(username: String): UserEntity
    suspend fun deleteUsers()
}

class UserRepositoryImpl(
    private val cache: UserCacheDataSource,
    private val network: UserNetworkDataSource,
) : UserRepository {

    private val TAG = UserRepositoryImpl::class.java.simpleName

    override suspend fun getUsers(): Flow<DataState<List<UserEntity>>> = flow {
        emit(DataState.Loading)
        try {
            val reponse = network.getUsers()
            for (user in reponse) {
                saveUsers(user)
            }
            emit(
                DataState.Success(
                    getUsersDB()
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    override suspend fun getUsersDB(): List<UserEntity> {
        return cache.getUsers()
    }

    override suspend fun saveUsers(user: UserModel): Long {
        return cache.insertUser(UserMap.toDB(user))
    }

    override suspend fun getUserByUsername(username: String): UserEntity {
        return cache.getUserByUsername(username)
    }

    override suspend fun deleteUsers() {
        cache.deleteAllUsers()
    }
}