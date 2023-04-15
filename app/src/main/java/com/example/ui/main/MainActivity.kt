package com.example.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.net.ApiRetrofit
import com.example.travel.R
import com.example.travel.databinding.ActivityMainBinding
import com.example.ui.base.BaseActivity
import com.example.ui.launch.ExamplePagerAdapter
import com.example.ui.personal.PersonalCenterFragment
import com.example.ui.running.RunningFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var apiService: ApiRetrofit
    private val fragments = mutableMapOf<Int, Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initFragments()
        initListener()
    }

    override fun getLayoutId() = R.layout.activity_main

    private fun initData() {

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

    override fun onBackPressed() {
        goBackToDesktop()
        super.onBackPressed()
    }

    private fun goBackToDesktop() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }
}