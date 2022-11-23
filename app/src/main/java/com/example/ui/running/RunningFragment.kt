package com.example.ui.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.maps.AMap
import com.amap.api.maps.MapsInitializer
import com.example.travel.databinding.FragmentRunningBinding
import com.example.ui.base.BaseFragment
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MapsInitializer.updatePrivacyShow(requireActivity(), true, true)
        MapsInitializer.updatePrivacyAgree(requireActivity(), true)
        binding.mapview.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        binding.mapview.onCreate(savedInstanceState)
        initAMap()
        initListener()
        return binding.root
    }

    private fun initAMap() {
        if (mAMap == null) {
            mAMap = binding.mapview.map
        }
    }

    private fun initListener() {

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapview.onDestroy()
    }

}