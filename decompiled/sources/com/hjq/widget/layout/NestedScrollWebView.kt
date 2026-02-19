package com.hjq.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import kotlin.math.max

open class NestedScrollWebView : WebView, NestedScrollingChild {
    private var mChange = false
    private val mChildHelper = NestedScrollingChildHelper(this)
    private var mLastMotionY = 0
    private var mNestedOffsetY = 0
    private val mScrollConsumed = IntArray(2)
    private val mScrollOffset = IntArray(2)

    constructor(context: Context) : super(context) {
        isNestedScrollingEnabled = true
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        isNestedScrollingEnabled = true
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        isNestedScrollingEnabled = true
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        isNestedScrollingEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val obtain = MotionEvent.obtain(event)
        val actionMasked = event.actionMasked
        var handled = false
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            mNestedOffsetY = 0
        }
        val y = event.y.toInt()
        event.offsetLocation(0f, mNestedOffsetY.toFloat())
        if (actionMasked != MotionEvent.ACTION_DOWN) {
            if (actionMasked != MotionEvent.ACTION_UP) {
                if (actionMasked == MotionEvent.ACTION_MOVE) {
                    var deltaY = mLastMotionY - y
                    if (dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)) {
                        deltaY -= mScrollConsumed[1]
                        obtain.offsetLocation(0f, mScrollOffset[1].toFloat())
                        mNestedOffsetY += mScrollOffset[1]
                    }
                    mLastMotionY = y - mScrollOffset[1]
                    val scrollY = scrollY
                    val consumedY = max(0, scrollY + deltaY) - scrollY
                    if (dispatchNestedScroll(0, consumedY, 0, deltaY - consumedY, mScrollOffset)) {
                        mLastMotionY -= mScrollOffset[1]
                        obtain.offsetLocation(0f, mScrollOffset[1].toFloat())
                        mNestedOffsetY += mScrollOffset[1]
                    }
                    if (mScrollConsumed[1] == 0 && mScrollOffset[1] == 0) {
                        if (mChange) {
                            mChange = false
                            obtain.action = MotionEvent.ACTION_DOWN
                            super.onTouchEvent(obtain)
                        } else {
                            handled = super.onTouchEvent(obtain)
                        }
                        obtain.recycle()
                        return handled
                    }
                    if (mChange) {
                        return false
                    }
                    mChange = true
                    super.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0f, 0f, 0))
                    return false
                }
                if (
                    actionMasked != MotionEvent.ACTION_CANCEL &&
                    actionMasked != MotionEvent.ACTION_POINTER_DOWN &&
                    actionMasked != MotionEvent.ACTION_POINTER_UP
                ) {
                    return false
                }
            }
            stopNestedScroll()
            return super.onTouchEvent(event)
        }
        mChange = false
        mLastMotionY = y
        startNestedScroll(2)
        return super.onTouchEvent(event)
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }
}
