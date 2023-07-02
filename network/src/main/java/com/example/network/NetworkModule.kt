package com.example.network

import com.example.network.api.ApiRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Desc:
 * @author lijt
 * Created on 2023/6/18 00:46
 * Email:
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGithubApi() = ApiRetrofit.getInstance()
}