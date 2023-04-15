package com.example.ui.map

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.AMap
import com.example.travel.R
import com.example.travel.databinding.ActivityAmapBinding
import com.example.ui.base.BaseActivity
import com.example.utils.AMapLocationManager
import com.gyf.immersionbar.ImmersionBar

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/21 23:28
 * Email:
 */
class AMapActivity : BaseActivity<ActivityAmapBinding>() {
    private var mAMap: AMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .init()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_amap)
        initMapView(savedInstanceState)
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        AMapLocationClient.updatePrivacyAgree(this, true)
        AMapLocationClient.updatePrivacyShow(this, true, true)
        binding.mapview.onCreate(savedInstanceState)
        if (mAMap == null) {
            mAMap = binding.mapview.map
            AMapLocationManager.getInstance(this)
                .init(mAMap)
            AMapLocationManager.getInstance(this)
                .onStart()
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
        AMapLocationManager.getInstance(this).onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
        AMapLocationManager.getInstance(this).onStop()
    }
}