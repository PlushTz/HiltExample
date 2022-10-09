package com.example.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.hilt.R

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 15:24
 * Email: lijt@eetrust.com
 */
class ImageUtil {
    companion object {
        @JvmStatic
        fun loadPhoto(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .error(R.mipmap.photo_default)
                .into(imageView)
        }
    }
}