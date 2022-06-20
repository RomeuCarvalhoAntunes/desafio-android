package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.local.source.UserCacheDataSource
import com.picpay.desafio.android.data.mappers.UserMap
import com.picpay.desafio.android.data.network.source.UserNetworkDataSource
import com.picpay.desafio.android.data.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    suspend fun getUsers(): Flow<DataState<List<UserEntity>?>>
    suspend fun insertUser(user: UserEntity): Long
    suspend fun getAllUsers(
    ): List<UserEntity>?

    suspend fun getUserByUsername(
        username: String,
    ): UserEntity?

    suspend fun deleteUser(username: String): Int
}

class UserRepositoryImpl(
    private val cache: UserCacheDataSource,
    private val network: UserNetworkDataSource,
) : UserRepository {

    private val TAG = UserRepositoryImpl::class.java.simpleName

    override suspend fun getUsers(): Flow<DataState<List<UserEntity>?>> = flow {
        emit(DataState.Loading)
        try {
            val networkResponse = network.getUsers()
            if (networkResponse != null) {
                for (user in networkResponse) {
                    insertUser(UserMap.toDB(user))
                }
                val cachedUsers = cache.getAllUsers()
                if (cachedUsers == null) {
                    emit(DataState.Error(Exception("Could not find any contacts from cache ")))
                } else {
                    emit(DataState.Success(cache.getAllUsers()))
                }
            } else {
                emit(DataState.Error(Exception("Could not find any contacts from server")))
            }
        } catch (e: Exception) {
            flow {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun insertUser(user: UserEntity): Long {
        return cache.insertUser(user)
    }

    override suspend fun getAllUsers(): List<UserEntity>? {
        return cache.getAllUsers()
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return cache.getUserByUsername(username)
    }

    override suspend fun deleteUser(username: String): Int {
        return cache.deleteUser(username)
    }
}