package com.example.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.BaseApplication
import com.example.ui.launch.LaunchActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * Desc:
 * @author lijt
 * Created on 2022/11/23 16:36
 * Email: lijt@eetrust.com
 */

open class BaseFragment : Fragment() {

    private var mContext: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is AppCompatActivity) {
            this.mContext = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.mContext = null
    }
}