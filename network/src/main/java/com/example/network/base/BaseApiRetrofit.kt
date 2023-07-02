package com.example.network.base

import com.example.network.NetConst
import com.example.network.api.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 10:38
 * Email: lijt@eetrust.com
 */
open class BaseApiRetrofit {
    val mGitHubApi: GitHubApi by lazy {
        Retrofit.Builder()
            .baseUrl(NetConst.GITHUB_URL)
            .client(mGitHubClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    private val mGitHubClient: OkHttpClient by lazy {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }
}