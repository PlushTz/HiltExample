package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.database.entity.Search

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/14 14:02
 * Email: lijt@eetrust.com
 */
@Dao
interface SearchDao {
    @Insert
    suspend fun insert(search: Search)
}