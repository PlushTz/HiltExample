package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 13:41
 * Email: lijt@eetrust.com
 */
@Entity
data class Search(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    var name: String,
    var fullName: String,
    var description: String,
    var isPrivate: Boolean,
    var language: String,
    var company: String,
    var url: String,
    var start: Int,
    var forks: Int,
    var license: String
)