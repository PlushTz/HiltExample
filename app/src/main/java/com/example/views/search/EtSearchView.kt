package com.example.views.search

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView

/**
 * Desc:
 * @author lijt
 * Created on 2022/11/24 16:20
 * Email: lijt@eetrust.com
 */
class EtSearchView : SearchView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}