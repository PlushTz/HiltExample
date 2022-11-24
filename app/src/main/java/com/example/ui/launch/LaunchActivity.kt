package com.example.ui.launch

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.travel.R
import com.example.travel.databinding.ActivityLaunchBinding
import com.leaf.library.StatusBarUtil
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 9:52
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    private val permissions = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS)
    private lateinit var binding: ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_launch)
        StatusBarUtil.setTransparentForWindow(this)
        StatusBarUtil.setDarkMode(this)
        PermissionX.init(this)
            .permissions(permissions)
            .request { _, _, _ ->

            }
    }
}