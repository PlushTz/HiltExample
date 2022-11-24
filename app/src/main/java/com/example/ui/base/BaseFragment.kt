package com.example.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ui.dialog.ExampleDialogFragment
import com.qmuiteam.qmui.widget.QMUIEmptyView

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

    fun showDialog() {
        ExampleDialogFragment.getInstance()
            .show(parentFragmentManager, ExampleDialogFragment.TAG)
    }

    fun showLoadingView(){
        QMUIEmptyView(requireContext()).show(true)
    }
}