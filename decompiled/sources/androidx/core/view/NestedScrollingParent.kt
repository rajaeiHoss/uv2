package androidx.core.view

import android.view.View

interface NestedScrollingParent {
    fun getNestedScrollAxes(): Int

    fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean

    fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean

    fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray)

    fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
    )

    fun onNestedScrollAccepted(child: View, target: View, axes: Int)

    fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean

    fun onStopNestedScroll(target: View)
}
