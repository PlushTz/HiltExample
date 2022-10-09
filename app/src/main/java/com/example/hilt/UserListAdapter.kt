package com.example.hilt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.database.entity.User
import com.example.hilt.databinding.ItemUserBinding
import com.example.utils.ImageUtil

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 15:36
 * Email: lijt@eetrust.com
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    private val data = mutableListOf<User>()

    fun submit(data: MutableList<User>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflate = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserHolder(inflate)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = data[position]
        ImageUtil.loadPhoto(holder.binding.ivPhoto, user.photo)
        holder.binding.tvUsername.text = user.userName
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class UserHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}