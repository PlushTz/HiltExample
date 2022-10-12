package com.example.hilt.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.entity.User
import com.example.hilt.R
import com.example.hilt.databinding.ActivityUserListBinding
import com.example.itemdecoration.ItemDecoration
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
    private lateinit var binding: ActivityUserListBinding
    val viewModel: MainViewModel by viewModels()
    private val data = mutableListOf<User>()
    private var userListAdapter: UserListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        init()
    }

    private fun init() {
        userListAdapter = UserListAdapter()
        userListAdapter?.setOnItemClickListener(object : UserListAdapter.OnItemClickListener {
            override fun onClickListener(bean: User, position: Int) {

            }

            override fun onLongClickListener(bean: User, position: Int) {

            }
        })
        binding.rvUserList.adapter = userListAdapter
        binding.rvUserList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = ItemDecoration(this, ItemDecoration.VERTICAL, false, null)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.item_divider_bg))
        binding.rvUserList.addItemDecoration(itemDecoration)
        lifecycleScope.launch {
            viewModel.queryAllUser()
        }
        viewModel.users.observe(this) {
            data.addAll(it)
            userListAdapter?.submit(data)
        }
    }
}