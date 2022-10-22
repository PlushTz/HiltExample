package com.example.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.travel.R
import com.example.travel.databinding.DialogExampleBinding

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/21 10:06
 * Email: lijt@eetrust.com
 */
class ExampleDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "ExampleDialogFragment"
    }

    lateinit var binding: DialogExampleBinding

    private var mOnDialogClickListener: OnDialogClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate")
        initWindow()
    }

    private fun initWindow() {
        val window = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.attributes?.width = 1000
        window?.attributes?.height = 300
        window?.attributes?.gravity = Gravity.CENTER
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d("TAG", "onCreateView")
        binding = DialogExampleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("TAG", "onCreateDialog")
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_example, null)
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }

    fun setOnDialogClickListener(listener: OnDialogClickListener) {
        mOnDialogClickListener = listener
    }

    interface OnDialogClickListener {
        fun onCommit()

        fun onCancel()
    }
}