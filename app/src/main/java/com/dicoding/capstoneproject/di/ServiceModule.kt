package com.dicoding.capstoneproject.di

import com.dicoding.capstoneproject.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideJakiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}