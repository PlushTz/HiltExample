package com.example.net

import com.ayvytr.okhttploginterceptor.LoggingInterceptor
import com.ayvytr.okhttploginterceptor.Priority
import com.ayvytr.okhttploginterceptor.printer.IPrinter
import okhttp3.OkHttpClient
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
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor(showLog = true, isShowAll = false, tag = "ShLog--", priority = Priority.D, visualFormat = true, maxLineLength = Int.MAX_VALUE, object : IPrinter {
                override fun print(priority: Priority, tag: String, msg: String) {

                }
            }))
            .build()
    }
}