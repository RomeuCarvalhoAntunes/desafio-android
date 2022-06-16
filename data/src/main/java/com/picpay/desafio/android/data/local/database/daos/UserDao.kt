package com.picpay.desafio.android.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.local.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM user")
    suspend fun getUsers(
    ): List<UserEntity>

    @Query("SELECT * FROM user WHERE username=:username")
    suspend fun getUserByUsername(
        username: String,
    ): UserEntity

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}