package com.example.net

import com.example.response.SearchResponse

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 10:38
 * Email: lijt@eetrust.com
 */
class ApiRetrofit : BaseApiRetrofit() {
    companion object {
        @Volatile
        private var instance: ApiRetrofit? = null
        fun getInstance(): ApiRetrofit = synchronized(this) {
            instance ?: ApiRetrofit().also { instance = it }
        }
    }

    suspend fun searchRepos(query: String, page: Int = 1, itemsPerPage: Int = 1): SearchResponse {
        return mGitHubApi.searchRepos(query, page, itemsPerPage)
    }
}