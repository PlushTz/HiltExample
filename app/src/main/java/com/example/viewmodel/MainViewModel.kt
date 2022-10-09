package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.database.entity.User
import com.example.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 10:13
 * Email: lijt@eetrust.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    val user: MutableLiveData<User> = MutableLiveData()

    suspend fun insert(user: User) {
        repository.insert(user)
    }

    suspend fun queryAllUser() {
        repository.queryAllUser()
            .onStart {
                Log.d("ShLog---", "onStart")
            }
            .onCompletion {
                Log.d("ShLog---", "onCompletion")
            }
            .collect {
                users.postValue(it)
            }
    }

    suspend fun deleteAllUser(user: User) {
        repository.deleteAllUser(user)
    }

    suspend fun queryUserById(id: Int) {
        repository.queryUserById(id)
            .onStart { }
            .onCompletion { }
            .collect { user.postValue(it) }
    }
}