package com.example.ui.running

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amap.api.maps.AMap
import com.example.net.ApiRetrofit
import com.example.travel.databinding.FragmentRunningBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 16:36
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class RunningFragment : Fragment() {
    private lateinit var binding: FragmentRunningBinding

    @Inject
    lateinit var apiRetrofit: ApiRetrofit
    private var mAMap: AMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        binding.mapview.onCreate(savedInstanceState)
        initAMap()
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
        Log.d("TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
        Log.d("TAG", "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
        Log.d("TAG", "onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
        Log.d("TAG", "onDestroy")
    }
}