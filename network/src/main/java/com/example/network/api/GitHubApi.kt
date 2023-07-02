package com.example.network.api

import com.example.network.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 10:38
 * Email: lijt@eetrust.com
 */
interface GitHubApi {
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(@Query("q") query: String, @Query("page") page: Int = 10, @Query("per_page") itemsPerPage: Int = 10): SearchResponse
}