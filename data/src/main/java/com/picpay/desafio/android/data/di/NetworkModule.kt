package com.picpay.desafio.android.data.di

import androidx.viewbinding.BuildConfig
import com.picpay.desafio.android.data.network.services.EndpointPicPay
import com.picpay.desafio.android.data.network.services.UserService
import com.picpay.desafio.android.data.network.source.UserNetworkDataSource
import com.picpay.desafio.android.data.network.source.UserNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesBaseURL() = EndpointPicPay.BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .client(okHttpClient).build()


    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit) =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesUserNetworkDataSource(service: UserService):
            UserNetworkDataSource =
        UserNetworkDataSourceImpl(service)
}