package com.example.hilt

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.entity.User
import com.example.hilt.databinding.ActivitySecBinding
import com.example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 16:54
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecBinding
    val viewModel: MainViewModel by viewModels()
    private val data = mutableListOf<User>()
    private var userListAdapter: UserListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sec)
        init()
    }

    private fun init() {
        userListAdapter = UserListAdapter()
        binding.rvUserList.adapter = userListAdapter
        binding.rvUserList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launch {
            viewModel.queryAllUser()
        }
        viewModel.users.observe(this) {
            data.addAll(it)
            userListAdapter?.submit(data)
        }
    }
}