package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.usecases.UserUseCases
import com.picpay.desafio.android.domain.usecases.UserUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun providesUserUseCases(
        repository: UserRepository
    ): UserUseCases {
        return UserUseCasesImpl(repository)
    }
}
