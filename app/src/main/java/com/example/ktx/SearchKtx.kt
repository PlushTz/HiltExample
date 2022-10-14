package com.example.ktx

import com.example.database.entity.Search
import com.example.response.Item

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 13:50
 * Email: lijt@eetrust.com
 */
fun Search.build(response: Item): Search {
    this.name = response.name
    this.fullName = response.fullName
    this.description = response.description
    this.isPrivate = response.private
    this.language = response.language
    this.company = response.owner.login
    this.url = response.htmlUrl
    this.start = response.stargazersCount
    this.forks = response.forksCount
    this.license = response.license.name
    return this
}