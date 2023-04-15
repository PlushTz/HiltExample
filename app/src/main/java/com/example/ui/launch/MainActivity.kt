package com.example.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.travel.R
import com.example.travel.databinding.ActivityLaunchBinding

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 9:52
 * Email: lijt@eetrust.com
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_launch)
    }
}