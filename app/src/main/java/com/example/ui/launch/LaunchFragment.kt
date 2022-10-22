package com.example.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travel.R
import com.example.travel.databinding.FragmentLaunchBinding
import com.example.ui.personal.PersonalCenterFragment
import com.example.ui.running.RunningFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 10:02
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class LaunchFragment : Fragment() {
    private lateinit var binding: FragmentLaunchBinding

    private val fragments = mutableMapOf<Int, Fragment>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLaunchBinding.inflate(inflater, container, false)
        initFragments()
        initListener()
        return binding.root
    }

    private fun initFragments() {
        fragments[0] = RunningFragment()
        fragments[1] = PersonalCenterFragment()
    }

    private fun initListener() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = ExamplePagerAdapter(this, fragments)
        viewPager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            0 -> R.drawable.drawable_home_tab
            1 -> R.drawable.drawable_personal_tab
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> "running"
            1 -> "personal"
            else -> null
        }
    }
}