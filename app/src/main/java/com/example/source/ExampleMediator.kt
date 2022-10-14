package com.example.source

import androidx.paging.*
import androidx.room.withTransaction
import com.example.database.ExampleDatabase
import com.example.database.entity.Search
import com.example.database.entity.User
import com.example.ktx.build
import com.example.net.GitHubApi
import com.example.response.SearchResponse

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 10:19
 * Email: lijt@eetrust.com
 */
@OptIn(ExperimentalPagingApi::class)
class ExampleMediator(
    private val query: String,
    private val gitHubApi: GitHubApi,
    private val database: ExampleDatabase
) : RemoteMediator<Int, Search>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Search>): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val searchResponse = gitHubApi.searchRepos(query)
                val items = searchResponse.items
                items.forEach { item ->
                    val search = Search(
                        null,
                        name = item.name,
                        fullName = item.fullName,
                        description = item.description,
                        isPrivate = item.private,
                        language = item.language,
                        company = item.owner.login,
                        url = item.htmlUrl,
                        start = item.stargazersCount,
                        forks = item.forksCount,
                        license = item.license.name
                    )
                    database.searchDao.insert(search)
                }
                if (items.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                searchResponse.totalCount
            }
        }
        return MediatorResult.Success(endOfPaginationReached = false)
    }

}