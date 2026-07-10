package androidx.core.view

import android.view.View

interface NestedScrollingParent2 : NestedScrollingParent {
    fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int)

    fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
    )

    fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int)

    fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean

    fun onStopNestedScroll(target: View, type: Int)
}
