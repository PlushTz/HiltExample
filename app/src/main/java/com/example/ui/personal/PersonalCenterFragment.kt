package com.example.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travel.databinding.FragmentPersonalBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 16:37
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class PersonalCenterFragment : Fragment() {
    private lateinit var binding:FragmentPersonalBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }
}