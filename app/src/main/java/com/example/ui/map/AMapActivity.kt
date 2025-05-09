package com.example.ui.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.AMap
import com.example.constant.DataStoreConst
import com.example.network.api.ApiRetrofit
import com.example.network.api.GitHubApi
import com.example.travel.R
import com.example.travel.databinding.ActivityAmapBinding
import com.example.travel.databinding.DialogBottomLayerBinding
import com.example.ui.base.BaseActivity
import com.example.utils.AMapLocationManager
import com.example.utils.AMapManager
import com.example.utils.DataStore
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gyf.immersionbar.ImmersionBar
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/21 23:28
 * Email:
 */
@AndroidEntryPoint
class AMapActivity : BaseActivity<ActivityAmapBinding>() {
    private val viewModel: AMapViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AMapActivity::class.java))
        }
    }

    private val permissions = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var mAMap: AMap? = null

    @Volatile
    private var isLauncher: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .init()
        if (PermissionX.isGranted(this, Manifest.permission.ACCESS_FINE_LOCATION) && PermissionX.isGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION) && PermissionX.isGranted(this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS) && PermissionX.isGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE) && PermissionX.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_amap)
        initMapView(savedInstanceState)
        initListener()
        initData()
    }

    private fun initData() {
        viewModel.queryTrafficLayer()
        viewModel.querySatelliteLayer()
        viewModel.queryNightLayer()

        viewModel.trafficLayer.observe(this@AMapActivity) {
            mAMap?.isTrafficEnabled = it
        }

        viewModel.nightLayer.observe(this@AMapActivity) {
            mAMap?.mapType = if (it) AMap.MAP_TYPE_NIGHT else AMap.MAP_TYPE_SATELLITE
        }



    }

    private fun initListener() {
        binding.tvMenuLayer.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.MyBottomSheetDialogBgStyle)
            val binding: DialogBottomLayerBinding = DialogBottomLayerBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(binding.root)
            bottomSheetDialog.show()
            reloadLayer(binding)
            binding.cbLayerTraffic.setOnCheckedChangeListener { _, isChecked ->
                viewModel.switchTrafficLayer(isChecked)
                reloadLayer(binding)
            }

            binding.cbLayerSatellite.setOnCheckedChangeListener { _, isChecked ->
                viewModel.switchSatelliteLayer(isChecked)
                reloadLayer(binding)
                if (isChecked) {
                    mAMap?.mapType = AMap.MAP_TYPE_SATELLITE
                    binding.cbLayerNight.isChecked = false
                }
            }

            binding.cbLayerNight.setOnCheckedChangeListener { _, isChecked ->
                viewModel.switchNightLayer(isChecked)
                reloadLayer(binding)
//                viewModel.search("android")
                if (isChecked) {
                    mAMap?.mapType = AMap.MAP_TYPE_NIGHT
                    binding.cbLayerSatellite.isChecked = false
                }
            }
        }
    }

    /**
     * 初始化图层
     * @param binding DialogBottomLayerBinding
     * @return Unit
     */

    private fun reloadLayer(binding: DialogBottomLayerBinding) {
        viewModel.trafficLayer.observe(this@AMapActivity) {
            binding.cbLayerTraffic.isChecked = it
        }

        viewModel.nightLayer.observe(this@AMapActivity) {
            binding.cbLayerNight.isChecked = it
        }
        viewModel.satelliteLayer.observe(this@AMapActivity) {
            binding.cbLayerSatellite.isChecked = it
        }
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        AMapLocationClient.updatePrivacyAgree(this, true)
        AMapLocationClient.updatePrivacyShow(this, true, true)
        binding.mapview.onCreate(savedInstanceState)
        if (mAMap == null) {
            mAMap = binding.mapview.map
            AMapLocationManager.getInstance()
                .init(mAMap, this)
            AMapManager.getInstance()
                .init(mAMap)
            AMapLocationManager.getInstance()
                .onStart()
            AMapLocationManager.getInstance()
                .setOnLocationChangedListener(object : AMapLocationManager.OnLocationChangedListener {
                    override fun onLocationChanged(location: AMapLocation?) {
                        //获取当前位置的经度
                        val longitude = location?.longitude!!
                        //获取当前位置的纬度
                        val latitude = location.latitude
                        if (isLauncher) {
                            isLauncher = false
                            AMapManager.getInstance()
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
        AMapLocationManager.getInstance()
            .onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
        AMapLocationManager.getInstance()
            .onStop()
    }
}