package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.utils.DataState
import kotlinx.coroutines.flow.Flow

interface UserUseCases {
    suspend fun deleteUser(username: String): Int
    suspend fun addNewUser(user: UserEntity): Long
    suspend fun getUsersFromServer(): Flow<DataState<List<UserEntity>?>>
//    suspend fun syncUsersWithServer()
}

class UserUseCasesImpl(private val repository: UserRepository) : UserUseCases {
    override suspend fun addNewUser(user: UserEntity): Long {
        return repository.insertUser(user)
    }

    override suspend fun deleteUser(username: String): Int {
        return repository.deleteUser(username)
    }

    override suspend fun getUsersFromServer(): Flow<DataState<List<UserEntity>?>> {
        return repository.getUsersFromServer()
    }
    //    override suspend fun syncUsersWithServer() {
//        // todo ex
//    }
}
