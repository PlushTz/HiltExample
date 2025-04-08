package com.example.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowManager

/**
 * Desc:
 * Created by SunHang on 18-4-2.
 * Email：sunh@eetrust.com
 */

object DisplayUtil {

    /**
     * 将dp或dp值转换为px值，保证尺寸大小不变
     *
     * @param dpValue
     * @param scale
     * （DisplayMetrics类中属性density）
     * @return
     */
    fun dp2px(dpValue: Float, context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val scale = displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * sp转换成px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }


    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    fun getScreenMetrics(context: Context): Point {
        val dm = context.resources.displayMetrics
        val w_screen = dm.widthPixels
        val h_screen = dm.heightPixels
        return Point(w_screen, h_screen)

    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    fun getScreenRate(context: Context): Float {
        val p = getScreenMetrics(context)
        val h = p.y.toFloat()
        val w = p.x.toFloat()
        return h / w
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    @JvmStatic
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    @JvmStatic
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    private fun getStatusBarHeight(context: Context): Int {
        var result = 0
        try {
            val resourceId =
                    context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }

        return result
    }


    fun getScreenSize(context: Context, useDeviceSize: Boolean): IntArray {

        val size = IntArray(2)

        val w = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = w.defaultDisplay
        val metrics = DisplayMetrics()
        d.getMetrics(metrics)
        // since SDK_INT = 1;
        var widthPixels = metrics.widthPixels
        var heightPixels = metrics.heightPixels

        if (!useDeviceSize) {
            size[0] = widthPixels
            size[1] = heightPixels - getStatusBarHeight(context)

            return size
        }


        // includes window decorations (statusbar bar/menu bar)
        try {
            val realSize = Point()
            Display::class.java.getMethod("getRealSize", Point::class.java).invoke(d, realSize)
            widthPixels = realSize.x
            heightPixels = realSize.y
        } catch (t: Throwable) {
            t.printStackTrace()
        }

        size[0] = widthPixels
        size[1] = heightPixels
        return size
    }

    fun fitsSystemWindows(isTranslucentStatus: Boolean, view: View) {
        if (isTranslucentStatus) {
            view.layoutParams.height = calcStatusBarHeight(view.context)
        }
    }

    private fun calcStatusBarHeight(context: Context): Int {
        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height").get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight
    }

    fun getScreenDpi(context: Context): ScreenDpi {
        val dm = context.resources.displayMetrics
        val densityDpi = dm.densityDpi
        return if (densityDpi <= ScreenDpi.HDPI.dpi) {
            ScreenDpi.HDPI
        } else if (densityDpi <= ScreenDpi.XHDPI.dpi) {
            ScreenDpi.XHDPI
        } else if (densityDpi <= ScreenDpi.XXHDPI.dpi) {
            ScreenDpi.XXHDPI
        } else {
            ScreenDpi.XXXHDPI
        }
    }


}
