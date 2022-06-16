package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.local.database.PicPayDatabase
import com.picpay.desafio.android.data.local.source.UserCacheDataSource
import com.picpay.desafio.android.data.local.source.UserCacheDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun providesUserCacheDataSource(database: PicPayDatabase): UserCacheDataSource {
        return UserCacheDataSourceImp(database)
    }
}