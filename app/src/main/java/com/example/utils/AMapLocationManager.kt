package com.example.utils

import android.content.Context
import android.graphics.Color
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.MyLocationStyle
import com.example.travel.R

/**
 * Desc:
 * @author lijt
 * Created on 2022/11/24 12:55
 * Email: lijt@eetrust.com
 */
class AMapLocationManager constructor(private val context: Context) : AMapLocationListener {

    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var mLocationStyle: MyLocationStyle? = null
    private var mUiSettings: UiSettings? = null
    private var mOnLocationChangedListener: OnLocationChangedListener? = null

    companion object {
        private var instance: AMapLocationManager? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AMapLocationManager(context).also { instance = it }
        }
    }

    fun init(aMap: AMap?) {
        mLocationClient = AMapLocationClient(context)
        mLocationOption = AMapLocationClientOption()
        mLocationStyle = MyLocationStyle()
        mLocationStyle?.interval(5000)
        mLocationStyle?.strokeWidth(0f)
        mLocationStyle?.strokeColor(Color.TRANSPARENT)
        mLocationStyle?.radiusFillColor(Color.TRANSPARENT)
        mLocationStyle?.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER)
        mLocationStyle?.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon))
        aMap?.myLocationStyle = mLocationStyle
        aMap?.isMyLocationEnabled = true
        mUiSettings = aMap?.uiSettings
        mUiSettings?.isMyLocationButtonEnabled = false
        mUiSettings?.isScaleControlsEnabled = false
        mUiSettings?.isZoomControlsEnabled = false
        mLocationClient?.setLocationListener(this)
        mLocationOption?.isNeedAddress = true
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationOption?.interval = 2000
        mLocationClient?.setLocationOption(mLocationOption)
    }

    /**
     * 设置定位图标
     * @param icon Int
     */
    fun setLocationIcon(icon: Int) {
        mLocationStyle?.myLocationIcon(BitmapDescriptorFactory.fromResource(icon))
    }

    fun onStart() {
        mLocationClient?.startLocation()
    }

    fun setOnLocationChangedListener(locationChangedListener: OnLocationChangedListener) {
        this.mOnLocationChangedListener = locationChangedListener
    }

    override fun onLocationChanged(location: AMapLocation?) {
        mOnLocationChangedListener?.onLocationChanged(location)
    }

    interface OnLocationChangedListener {
        fun onLocationChanged(location: AMapLocation?)
    }

    fun onStop() {
        mLocationClient?.stopLocation()
        mLocationClient?.onDestroy()
    }

}