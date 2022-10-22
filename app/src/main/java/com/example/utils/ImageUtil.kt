package com.example.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.travel.R

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

        @JvmStatic
        fun loadRoundPhoto(imageView: ImageView,url: String){
            Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                .error(R.mipmap.photo_default)
                .into(imageView)
        }
    }
}