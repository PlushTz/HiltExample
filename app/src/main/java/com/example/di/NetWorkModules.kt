package com.example.di

import com.example.net.ApiRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 11:27
 * Email: lijt@eetrust.com
 */
@Module
@InstallIn(SingletonComponent::class)
class NetWorkModules {
    @Provides
    @Singleton
    fun provideGitHubNet(): ApiRetrofit {
        return ApiRetrofit.getInstance()
    }
}