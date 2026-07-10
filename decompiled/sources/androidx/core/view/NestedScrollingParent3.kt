package androidx.core.view

import android.view.View

interface NestedScrollingParent3 : NestedScrollingParent2 {
    fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray,
    )
}
