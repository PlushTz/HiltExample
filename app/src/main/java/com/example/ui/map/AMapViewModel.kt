package com.example.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.api.ApiRetrofit
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
    fun search(keyword: String){
        viewModelScope.launch {
            try {
                apiRetrofit.searchRepos(keyword)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}