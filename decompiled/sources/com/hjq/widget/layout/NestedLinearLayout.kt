package com.hjq.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewParent
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.NestedScrollingParent
import androidx.core.view.NestedScrollingParentHelper
import kotlin.math.abs

open class NestedLinearLayout : LinearLayout, NestedScrollingChild, NestedScrollingParent {
    private var mActivePointerId = INVALID_POINTER
    private val mChildHelper: NestedScrollingChildHelper
    private var mIsBeingDragged = false
    private var mLastMotionY = 0
    private val mMaximumVelocity: Float
    private val mMinimumVelocity: Float
    private val mParentHelper: NestedScrollingParentHelper
    private val mScrollConsumed: IntArray
    private val mScrollOffset: IntArray
    private val mTouchSlop: Float
    private var mVelocityTracker: VelocityTracker? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mScrollConsumed = IntArray(2)
        mScrollOffset = IntArray(2)
        setWillNotDraw(false)
        mChildHelper = NestedScrollingChildHelper(this)
        mParentHelper = NestedScrollingParentHelper(this)
        isNestedScrollingEnabled = true
        val configuration = ViewConfiguration.get(context)
        mTouchSlop = configuration.scaledTouchSlop.toFloat()
        mMaximumVelocity = configuration.scaledMaximumFlingVelocity.toFloat()
        mMinimumVelocity = configuration.scaledMinimumFlingVelocity.toFloat()
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        return (axes and 2) != 0
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val obtain = MotionEvent.obtain(event)
        initVelocityTrackerIfNotExists()
        val actionMasked = obtain.actionMasked
        if (actionMasked != MotionEvent.ACTION_DOWN) {
            if (actionMasked != MotionEvent.ACTION_UP) {
                if (actionMasked == MotionEvent.ACTION_MOVE) {
                    val pointerIndex = obtain.findPointerIndex(mActivePointerId)
                    if (pointerIndex != -1) {
                        val y = obtain.getY(pointerIndex).toInt()
                        var deltaY = mLastMotionY - y
                        if (dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)) {
                            deltaY -= mScrollConsumed[1]
                            obtain.offsetLocation(0f, mScrollOffset[1].toFloat())
                        }
                        if (!mIsBeingDragged && abs(mLastMotionY - y).toFloat() > mTouchSlop) {
                            val parent: ViewParent? = parent
                            parent?.requestDisallowInterceptTouchEvent(true)
                            mIsBeingDragged = true
                            deltaY =
                                if (deltaY > 0) {
                                    (deltaY.toFloat() - mTouchSlop).toInt()
                                } else {
                                    (deltaY.toFloat() + mTouchSlop).toInt()
                                }
                        }
                        if (mIsBeingDragged) {
                            mVelocityTracker?.addMovement(event)
                            mLastMotionY = y - mScrollOffset[1]
                            if (dispatchNestedScroll(0, 0, 0, deltaY, mScrollOffset)) {
                                mLastMotionY -= mScrollOffset[1]
                                obtain.offsetLocation(0f, mScrollOffset[1].toFloat())
                            }
                        }
                    }
                } else if (actionMasked != MotionEvent.ACTION_CANCEL) {
                    if (actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
                        val actionIndex = obtain.actionIndex
                        mLastMotionY = obtain.getY(actionIndex).toInt()
                        mActivePointerId = obtain.getPointerId(actionIndex)
                    } else if (actionMasked == MotionEvent.ACTION_POINTER_UP) {
                        onSecondaryPointerUp(obtain)
                        mLastMotionY = obtain.getY(obtain.findPointerIndex(mActivePointerId)).toInt()
                    }
                }
            }
            if (mIsBeingDragged) {
                mVelocityTracker?.computeCurrentVelocity(1000, mMaximumVelocity)
                val yVelocity = mVelocityTracker?.getYVelocity(mActivePointerId)?.toInt() ?: 0
                if (abs(yVelocity).toFloat() > mMinimumVelocity) {
                    flingWithNestedDispatch(-yVelocity)
                }
            }
            mActivePointerId = INVALID_POINTER
            endDrag()
        } else {
            mVelocityTracker?.addMovement(event)
            mLastMotionY = obtain.y.toInt()
            mActivePointerId = obtain.getPointerId(0)
            startNestedScroll(2)
        }
        obtain.recycle()
        return true
    }

    private fun onSecondaryPointerUp(event: MotionEvent) {
        val actionIndex = event.actionIndex
        if (event.getPointerId(actionIndex) == mActivePointerId) {
            val newIndex = if (actionIndex == 0) 1 else 0
            mLastMotionY = event.getY(newIndex).toInt()
            mActivePointerId = event.getPointerId(newIndex)
            mVelocityTracker?.clear()
        }
    }

    private fun endDrag() {
        mIsBeingDragged = false
        recycleVelocityTracker()
        stopNestedScroll()
    }

    private fun flingWithNestedDispatch(velocityY: Int) {
        val velocity = velocityY.toFloat()
        if (!dispatchNestedPreFling(0f, velocity)) {
            dispatchNestedFling(0f, velocity, true)
        }
    }

    private fun initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
    }

    private fun recycleVelocityTracker() {
        val velocityTracker = mVelocityTracker
        if (velocityTracker != null) {
            velocityTracker.recycle()
            mVelocityTracker = null
        }
    }

    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        if (disallowIntercept) {
            recycleVelocityTracker()
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept)
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

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        mParentHelper.onNestedScrollAccepted(child, target, axes)
        startNestedScroll(2)
    }

    override fun onStopNestedScroll(target: View) {
        mParentHelper.onStopNestedScroll(target)
        stopNestedScroll()
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        dispatchNestedPreScroll(dx, dy, consumed, null)
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun getNestedScrollAxes(): Int {
        return mParentHelper.nestedScrollAxes
    }

    companion object {
        private const val INVALID_POINTER = -1
    }
}
