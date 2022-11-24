package com.example.ui.dialog

import android.os.Bundle
import android.view.*
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

        private var instance: ExampleDialogFragment? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ExampleDialogFragment().also { instance = it }
        }
    }

    lateinit var binding: DialogExampleBinding

    private var mOnDialogClickListener: OnDialogClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogFullScreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogExampleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        dialog?.apply {
            window?.apply {
                attributes.run {
                    width = WindowManager.LayoutParams.WRAP_CONTENT
                    height = WindowManager.LayoutParams.WRAP_CONTENT
                    gravity = Gravity.CENTER
                }
                setCancelable(true)
                setCanceledOnTouchOutside(true)
            }
        }
    }

    fun setOnDialogClickListener(listener: OnDialogClickListener) {
        mOnDialogClickListener = listener
    }

    interface OnDialogClickListener {
        fun onCommit()

        fun onCancel()
    }
}