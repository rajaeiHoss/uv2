package com.hjq.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class NoScrollViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun setCurrentItem(item: Int) {
        val smoothScroll = abs(currentItem - item) == 1
        super.setCurrentItem(item, smoothScroll)
    }
}
