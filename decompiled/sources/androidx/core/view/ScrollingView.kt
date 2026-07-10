package androidx.core.view

interface ScrollingView {
    fun computeHorizontalScrollExtent(): Int

    fun computeHorizontalScrollOffset(): Int

    fun computeHorizontalScrollRange(): Int

    fun computeVerticalScrollExtent(): Int

    fun computeVerticalScrollOffset(): Int

    fun computeVerticalScrollRange(): Int
}
