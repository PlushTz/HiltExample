package com.example.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster
import com.hjq.toast.style.BlackToastStyle

/**
 * Desc:
 * @author lijt
 * Created on 2023/4/10 23:01
 * Email:
 */
abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: D

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ImmersionBar.with(this).statusBarDarkFont(true)
            .init()
        val layoutId = getLayoutId()
        if (layoutId != null) {
            binding = DataBindingUtil.setContentView(this, layoutId)
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int?

    fun showToast(msg: String) {
        val params = ToastParams()
        params.text = msg
        params.style = BlackToastStyle()
        Toaster.show(params)
    }
}