package com.example.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.travel.R
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
    private lateinit var binding: FragmentPersonalBinding

    companion object {
        const val PHOTO = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F26%2F20200326110420_ylpyg.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669990665&t=6d8e08c4250baeb72a04071f0cffea63"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.ivPhoto.load(PHOTO) {
            crossfade(true)
            placeholder(R.mipmap.photo_default)
            transformations(RoundedCornersTransformation(5f))
        }
    }
}