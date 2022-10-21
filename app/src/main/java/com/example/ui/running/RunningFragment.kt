package com.example.ui.running

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.hilt.databinding.FragmentRunningBinding
import com.example.net.ApiRetrofit
import com.example.net.GitHubApi
import com.example.ui.dialog.ExampleDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.btnRequest.setOnClickListener {
            lifecycleScope.launch {
                apiRetrofit.searchRepos("okhttp")
            }
        }

        binding.btnShowDialog.setOnClickListener {
            val dialog = ExampleDialogFragment()
            dialog.show(childFragmentManager, ExampleDialogFragment.TAG)
            dialog.setOnDialogClickListener(object : ExampleDialogFragment.OnDialogClickListener {
                override fun onCommit() {
                    Log.d("TAG", "onCommit")
                }

                override fun onCancel() {
                    Log.d("TAG", "onCancel")
                }
            })
        }
    }

    private fun init() {

    }
}