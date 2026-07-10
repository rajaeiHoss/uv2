package androidx.appcompat.widget

import android.graphics.Rect

interface FitWindowsViewGroup {
    interface OnFitSystemWindowsListener {
        fun onFitSystemWindows(insets: Rect)
    }

    fun setOnFitSystemWindowsListener(listener: OnFitSystemWindowsListener?)
}
