package androidx.core.view

import android.view.View

interface OnReceiveContentListener {
    fun onReceiveContent(view: View, payload: ContentInfoCompat): ContentInfoCompat?
}
