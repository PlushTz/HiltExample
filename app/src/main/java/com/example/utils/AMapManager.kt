package com.example.utils

import android.content.Context
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng

/**
 * Desc:
 * @author lijt
 * Created on 2023/4/16 23:02
 * Email:
 */
class AMapManager private constructor(val context: Context) {
    private var mAMap: AMap? = null

    companion object {
        private var instance: AMapManager? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AMapManager(context).also {
                instance = it
            }
        }
    }

    fun init(aMap: AMap?) {
        this.mAMap = aMap
    }

    fun moveCamera(latitude: Double?, longitude: Double?) {
        if (latitude == null || longitude == null) {
            return
        }
        val latLng = LatLng(latitude, longitude)
        mAMap?.moveCamera(CameraUpdateFactory.changeLatLng(latLng))
        mAMap?.moveCamera(CameraUpdateFactory.zoomTo(mAMap?.maxZoomLevel?.minus(3) ?: 0f))
    }

}