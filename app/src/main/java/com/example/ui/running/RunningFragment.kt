package com.example.ui.running

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.location.AMapLocation
import com.amap.api.maps.AMap
import com.amap.api.maps.MapsInitializer
import com.example.travel.databinding.FragmentRunningBinding
import com.example.ui.base.BaseFragment
import com.example.utils.AMapLocationManager
import com.example.views.behavior.GaoDeBottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 16:36
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class RunningFragment : BaseFragment() {
    private lateinit var binding: FragmentRunningBinding

    private var mAMap: AMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        binding.mapview.onCreate(savedInstanceState)
        initView()
        initAMap()
        return binding.root
    }

    private fun initView() {
        val behavior = GaoDeBottomSheetBehavior.from(binding.bottomSheet)
        behavior.setBottomSheetCallback(object : GaoDeBottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(var1: View, var2: Int) {

            }

            override fun onSlide(var1: View, var2: Float) {

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapsInitializer.updatePrivacyShow(requireActivity(), true, true)
        MapsInitializer.updatePrivacyAgree(requireActivity(), true)
        binding.mapview.onCreate(savedInstanceState)
        AMapLocationManager.getInstance(requireContext())
            .init(mAMap)
        AMapLocationManager.getInstance(requireContext())
            .onStart()
        initListener()
    }

    private fun initAMap() {
        if (mAMap == null) {
            mAMap = binding.mapview.map
        }
    }

    private fun initListener() {
        AMapLocationManager.getInstance(requireContext())
            .setOnLocationChangedListener(object : AMapLocationManager.OnLocationChangedListener {
                override fun onLocationChanged(location: AMapLocation?) {
                    Log.d("TAG", "$location")
                }
            })
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapview.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        AMapLocationManager.getInstance(requireContext())
            .onStop()
        binding.mapview.onDestroy()
    }

}