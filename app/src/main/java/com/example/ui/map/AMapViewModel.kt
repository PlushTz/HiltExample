package com.example.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constant.DataStoreConst
import com.example.network.api.ApiRetrofit
import com.example.utils.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Desc:
 * @author lijt
 * Created on 2023/6/18 19:04
 * Email:
 */
@HiltViewModel
class AMapViewModel @Inject constructor(private val apiRetrofit: ApiRetrofit) : ViewModel() {

    private val _trafficLayer = MutableLiveData<Boolean>()

    private val _nightLayer = MutableLiveData<Boolean>()

    private val _satelliteLayer = MutableLiveData<Boolean>()

    val trafficLayer: LiveData<Boolean> = _trafficLayer

    val nightLayer: LiveData<Boolean> = _nightLayer

    val satelliteLayer: LiveData<Boolean> = _satelliteLayer

    fun search(keyword: String) {
        viewModelScope.launch {
            try {
                apiRetrofit.searchRepos(keyword)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun switchTrafficLayer(isChecked: Boolean) {
        viewModelScope.launch {
            DataStore.putData(DataStoreConst.DS_LAYER_TRAFFIC, isChecked)
            _trafficLayer.value = isChecked
        }
    }

    fun queryTrafficLayer() {
        viewModelScope.launch {
            _trafficLayer.value = DataStore.getData(DataStoreConst.DS_LAYER_TRAFFIC, false)
        }
    }

    fun switchNightLayer(isChecked: Boolean) {
        viewModelScope.launch {
            DataStore.putData(DataStoreConst.DS_LAYER_NIGHT, isChecked)
            _nightLayer.value = isChecked
        }
    }

    fun queryNightLayer() {
        viewModelScope.launch {
            _nightLayer.value = DataStore.getData(DataStoreConst.DS_LAYER_NIGHT, false)
        }
    }

    fun switchSatelliteLayer(isChecked: Boolean) {
        viewModelScope.launch {
            DataStore.putData(DataStoreConst.DS_LAYER_SATELLITE, isChecked)
            _satelliteLayer.value = isChecked
        }
    }

    fun querySatelliteLayer() {
        viewModelScope.launch {
            _satelliteLayer.value = DataStore.getData(DataStoreConst.DS_LAYER_SATELLITE, false)
        }
    }
}