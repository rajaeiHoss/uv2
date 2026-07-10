package androidx.core.view

interface NestedScrollingChild {
    fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean

    fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean

    fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
    ): Boolean

    fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
    ): Boolean

    fun hasNestedScrollingParent(): Boolean

    fun isNestedScrollingEnabled(): Boolean

    fun setNestedScrollingEnabled(enabled: Boolean)

    fun startNestedScroll(axes: Int): Boolean

    fun stopNestedScroll()
}
