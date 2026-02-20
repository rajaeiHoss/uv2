package androidx.core.view

interface NestedScrollingChild3 : NestedScrollingChild2 {
    fun dispatchNestedScroll(
        consumedX: Int,
        consumedY: Int,
        unconsumedX: Int,
        unconsumedY: Int,
        offsetInWindow: IntArray?,
        type: Int,
        consumed: IntArray?
    )
}
