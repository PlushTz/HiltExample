package com.example.ui.map

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amap.api.maps.AMap
import com.example.travel.R
import com.example.travel.databinding.ActivityAmapBinding

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/21 23:28
 * Email:
 */
class AMapActivity : BaseMapActivity() {
    private lateinit var binding: ActivityAmapBinding
    private var mAMap: AMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_amap)
        binding.mapview.onCreate(savedInstanceState)
        if (mAMap == null) {
            mAMap = binding.mapview.map
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
    }
}