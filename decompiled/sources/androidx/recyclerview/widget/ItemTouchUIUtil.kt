package androidx.recyclerview.widget

import android.graphics.Canvas
import android.view.View

interface ItemTouchUIUtil {
    fun clearView(view: View)

    fun onDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        view: View,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    )

    fun onDrawOver(
        canvas: Canvas,
        recyclerView: RecyclerView,
        view: View,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    )

    fun onSelected(view: View)
}
