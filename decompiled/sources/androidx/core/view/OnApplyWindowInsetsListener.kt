package androidx.core.view

import android.view.View

interface OnApplyWindowInsetsListener {
    fun onApplyWindowInsets(view: View, insets: WindowInsetsCompat): WindowInsetsCompat
}
