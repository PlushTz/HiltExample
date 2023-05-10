package com.example.ui.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.travel.R
import com.example.ui.map.AMapActivity
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster
import com.hjq.toast.style.BlackToastStyle
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/11 15:44
 * Email: lijt@eetrust.com
 */
class SplashActivity : AppCompatActivity() {
    private val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        //设置共同沉浸式样式
        if (PermissionX.isGranted(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) && PermissionX.isGranted(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) && PermissionX.isGranted(
                this,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            ) && PermissionX.isGranted(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) && PermissionX.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ) {
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
        AMapActivity.start(this)
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(3000)
//            withContext(Dispatchers.Main) {
//                AMapActivity.start(this@SplashActivity)
//                finish()
//            }
//        }
//        lifecycleScope.launch {
//            flow {
//                var time: Int
//                for (i in 3 downTo 1) {
//                    time = i
//                    delay(1000L)
//                    emit(time)
//                }
//
//            }.flowOn(Dispatchers.IO)
//                .collect {
//                    showToast(it.toString())
//                    Log.d("TAG", "倒计时:$it")
//                    if (it == 1) {
//                        AMapActivity.start(this@SplashActivity)
//                        finish()
//                    }
//                }
//        }

    }

    fun showToast(msg: String) {
        val params = ToastParams()
        params.text = msg
        params.style = BlackToastStyle()
        Toaster.show(params)
    }
}