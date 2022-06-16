package com.picpay.desafio.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.picpay.desafio.android.data.local.database.daos.UserDao
import com.picpay.desafio.android.data.local.database.entities.UserEntity
import javax.inject.Singleton

@Singleton
@Database(
    version = 1,
    entities = [UserEntity::class]
)

//@TypeConverter(
//
//)

abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME: String = "PICPAY_DB"
    }
}