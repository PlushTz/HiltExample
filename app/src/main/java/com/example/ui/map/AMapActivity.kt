package com.example.ui.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.AMap
import com.amap.api.maps.model.LatLng
import com.example.travel.R
import com.example.travel.databinding.ActivityAmapBinding
import com.example.ui.base.BaseActivity
import com.example.utils.AMapLocationManager
import com.example.utils.AMapManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gyf.immersionbar.ImmersionBar
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/21 23:28
 * Email:
 */
@AndroidEntryPoint
class AMapActivity : BaseActivity<ActivityAmapBinding>() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AMapActivity::class.java))
        }
    }

    private val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private var mAMap: AMap? = null

    @Volatile
    private var isLauncher: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
            loadView(savedInstanceState)
        } else {
            PermissionX.init(this)
                .permissions(permissions)
                .request { allGranted, _, _ ->
                    if (allGranted) {
                        loadView(savedInstanceState)
                    }
                }
        }

    }

    private fun loadView(savedInstanceState: Bundle?) {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .init()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_amap)
        initMapView(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.tvMenuLayer.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.dialog_bottom_layer)
            bottomSheetDialog.window?.setDimAmount(0f)
            bottomSheetDialog.show()
        }
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        AMapLocationClient.updatePrivacyAgree(this, true)
        AMapLocationClient.updatePrivacyShow(this, true, true)
        binding.mapview.onCreate(savedInstanceState)
        if (mAMap == null) {
            mAMap = binding.mapview.map
            AMapLocationManager.getInstance(this)
                .init(mAMap)
            AMapManager.getInstance(this@AMapActivity)
                .init(mAMap)
            AMapLocationManager.getInstance(this)
                .onStart()
            AMapLocationManager.getInstance(this)
                .setOnLocationChangedListener(object :
                    AMapLocationManager.OnLocationChangedListener {
                    override fun onLocationChanged(location: AMapLocation?) {
                        //获取当前位置的经度
                        val longitude = location?.longitude!!
                        //获取当前位置的纬度
                        val latitude = location.latitude
                        if (isLauncher) {
                            isLauncher = false
                            AMapManager.getInstance(this@AMapActivity)
                                .moveCamera(latitude, longitude)
                        }
                    }
                })
        }
    }

    override fun getLayoutId() = R.layout.activity_amap

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
        AMapLocationManager.getInstance(this)
            .onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
        AMapLocationManager.getInstance(this)
            .onStop()
    }
}