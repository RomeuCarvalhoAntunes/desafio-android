package com.picpay.desafio.android.data.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.local.database.PicPayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): PicPayDatabase {
        return Room.databaseBuilder(
            context,
            PicPayDatabase::class.java,
            PicPayDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


}