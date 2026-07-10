package androidx.core.view

interface NestedScrollingChild2 : NestedScrollingChild {
    fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int,
    ): Boolean

    fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int,
    ): Boolean

    fun hasNestedScrollingParent(type: Int): Boolean

    fun startNestedScroll(axes: Int, type: Int): Boolean

    fun stopNestedScroll(type: Int)
}
