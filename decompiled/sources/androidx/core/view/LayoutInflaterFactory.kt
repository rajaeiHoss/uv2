package androidx.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

@java.lang.Deprecated
interface LayoutInflaterFactory {
    fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View?
}
