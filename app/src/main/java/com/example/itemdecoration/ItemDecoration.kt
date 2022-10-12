package com.example.itemdecoration

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/11 9:37
 * Email: lijt@eetrust.com
 */
class ItemDecoration() : RecyclerView.ItemDecoration() {
    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        val ATTRS = intArrayOf(R.attr.listDivider)
    }

    var hideItems: Array<Int>? = null
    var isShowBottomDivider: Boolean? = null
    var orientation: Int? = null
    var driver: Drawable? = null

    private val mBounds = Rect()

    constructor(context: Context, orientation: Int, isShowBottomDivider: Boolean, hideItems: Array<Int>?) : this() {
        this.hideItems = hideItems
        this.isShowBottomDivider = isShowBottomDivider
        val typedArray = context.obtainStyledAttributes(ATTRS)
        driver = typedArray.getDrawable(0)
        if (driver == null) {
            Log.w("TAG", "driver is null")
        }
        typedArray.recycle()
        setOrientation(orientation)
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     * @param orientation Int [HORIZONTAL] or [VERTICAL]
     */
    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL")
        }
        this.orientation = orientation
    }

    fun setDrawable(drawable: Drawable?) {
        if (drawable == null) {
            throw IllegalArgumentException("Drawable cannot be null.")
        }
        driver = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || driver == null) {
            return
        }
        if (orientation == VERTICAL) {
            drawVertical(c, parent, state)
        } else {
            drawHorizontal(c, parent, state)
        }
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }
        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(childView)
            if (isShowBottomDivider == true || childRealPosition < lastPosition) {
                if (isNeedHide(childRealPosition)) {
                    continue
                }
                parent.layoutManager?.getDecoratedBoundsWithMargins(childView, mBounds)
                val right = mBounds.right + childView.translationY.roundToInt()
                val left = right - (driver?.intrinsicWidth ?: 0)
                driver?.setBounds(left, top, right, bottom)
                driver?.draw(canvas)
            }
        }
        canvas.restore()
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(childView)
            if (isShowBottomDivider == true || childRealPosition < lastPosition) {
                if (isNeedHide(childRealPosition)) {
                    continue
                }
                parent.getDecoratedBoundsWithMargins(childView, mBounds)
                val bottom = mBounds.bottom + childView.translationY.roundToInt()
                val top = bottom - (driver?.intrinsicHeight ?: 0)
                driver?.setBounds(left, top, right, bottom)
                driver?.draw(canvas)
            }
        }
        canvas.restore()
    }

    private fun isNeedHide(position: Int?): Boolean {
        if (hideItems == null || hideItems?.isEmpty() == true || position == null) {
            return false
        }
        hideItems?.forEach { hideItem ->
            if (position == hideItem) {
                return true
            }
        }
        return false
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (driver == null) {
            outRect.set(0, 0, 0, 0)
        }
        if (orientation == VERTICAL) {
            val lastPosition = state.itemCount - 1
            val position = parent.getChildAdapterPosition(view)
            if ((isShowBottomDivider == true || position < lastPosition) && !isNeedHide(position)) {
                outRect.set(0, 0, 0, driver?.intrinsicHeight ?: 0)
            } else {
                outRect.set(0, 0, 0, 0)
            }
        } else {
            val lastPosition = state.itemCount - 1
            val position = parent.getChildAdapterPosition(view)
            if ((isShowBottomDivider == true || position < lastPosition) && !isNeedHide(position)) {
                outRect.set(0, 0, driver?.intrinsicWidth ?: 0, 0)
            } else {
                outRect.set(0, 0, 0, 0)
            }
        }
    }
}