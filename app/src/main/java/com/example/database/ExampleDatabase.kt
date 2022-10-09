package com.example.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.UserDao
import com.example.database.entity.User

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 10:04
 * Email: lijt@eetrust.com
 */
@Database(entities = [User::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)])
abstract class ExampleDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}