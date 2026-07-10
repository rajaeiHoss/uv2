package androidx.core.graphics.drawable

import android.graphics.drawable.Drawable

interface WrappedDrawable {
    fun getWrappedDrawable(): Drawable?

    fun setWrappedDrawable(drawable: Drawable?)
}
