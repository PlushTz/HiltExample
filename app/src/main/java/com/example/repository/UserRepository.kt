package com.example.repository

import com.example.database.AppDatabase
import com.example.database.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 10:10
 * Email: lijt@eetrust.com
 */
class UserRepository @Inject constructor(private val exampleDb: AppDatabase) {
    suspend fun insert(user: User) {
        exampleDb.userDao.insert(user)
    }

    suspend fun queryAllUser(): Flow<List<User>> {
        return flow {
            val users = exampleDb.userDao.queryAllUser()
            emit(users)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteAllUser(user: User) {
        exampleDb.userDao.deleteAllUser(user)
    }

    suspend fun queryUserById(id: Int): Flow<User> {
        return flow {
            val user = exampleDb.userDao.queryUserById(id)
            emit(user)
        }.flowOn(Dispatchers.IO)
    }
}