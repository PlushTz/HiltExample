package com.example.database.dao

import androidx.room.*
import com.example.database.entity.User

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 10:02
 * Email: lijt@eetrust.com
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    suspend fun queryAllUser(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun queryUserById(id: Int): User

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteAllUser(user: User)
}