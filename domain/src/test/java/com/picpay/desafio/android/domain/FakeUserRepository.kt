package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.mappers.UserMap
import com.picpay.desafio.android.data.network.models.UserModel
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FakeUserRepository(
    private val cacheSource: HashMap<String, UserEntity>?,
    private val networkSource: ArrayList<UserModel>?
) : UserRepository {

    override suspend fun getUsers(): Flow<DataState<List<UserEntity>?>> = flow {
        try {
            val networkResponse = networkSource
            if (networkResponse != null) {
                for (user in networkResponse) {
                    insertUser(UserMap.toDB(user))
                }
                val cachedUsers = cacheSource?.values?.toList()
                if (cachedUsers == null) {
                    emit(DataState.Error(Exception("Could not find any contacts from cache ")))
                } else {
                    emit(DataState.Success(cachedUsers))
                }
            } else {
                emit(DataState.Error(Exception(ERROR)))
            }
        } catch (e: Exception) {
            flow {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun insertUser(user: UserEntity): Long {
        return if (cacheSource?.containsKey(user.username) == true) {
            -1
        } else {
            cacheSource!![user.username] = user
            return 100
        }
    }

    override suspend fun getAllUsers(): List<UserEntity>? {
        return cacheSource?.values?.toList()
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return if (cacheSource == null) {
            null
        } else {
            cacheSource[username]
        }
    }

    override suspend fun deleteUser(username: String): Int {
        return 0
    }

    companion object {
        const val ERROR = "ERROR"
    }
}