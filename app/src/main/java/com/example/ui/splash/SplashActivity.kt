package com.example.ui.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.travel.R
import com.example.ui.map.AMapActivity
import com.gyf.immersionbar.ImmersionBar
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/11 15:44
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val permissions = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //设置共同沉浸式样式
        if (PermissionX.isGranted(this, Manifest.permission.ACCESS_FINE_LOCATION) && PermissionX.isGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION) && PermissionX.isGranted(this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS) && PermissionX.isGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE) && PermissionX.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            jumpMain()
        } else {
            PermissionX.init(this)
                .permissions(permissions)
                .request { allGranted, _, _ ->
                    if (allGranted) {
                        jumpMain()
                    }
                }
        }
    }

    private fun jumpMain() {
        lifecycleScope.launch {
            flow {
                delay(1000)
                emit(Any())
            }.flowOn(Dispatchers.IO)
                .collect {
                    startActivity(Intent(this@SplashActivity, AMapActivity::class.java))
                    finish()
                }
        }

    }
}