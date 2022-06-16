package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.local.source.UserCacheDataSource
import com.picpay.desafio.android.data.network.source.UserNetworkDataSource
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(
        cache: UserCacheDataSource,
        network: UserNetworkDataSource
    ): UserRepository {
        return UserRepositoryImpl(cache, network)
    }
}