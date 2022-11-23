package com.example.ui.launch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ui.main.MainActivity

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 16:19
 * Email: lijt@eetrust.com
 */
class ExamplePagerAdapter(fragment: FragmentActivity, private val table: Map<Int, Fragment>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return table.size
    }

    override fun createFragment(position: Int): Fragment {
        return table[position] ?: throw NullPointerException()
    }
}