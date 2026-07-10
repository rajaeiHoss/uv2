package androidx.core.graphics.drawable

import android.content.res.ColorStateList
import android.graphics.PorterDuff

interface TintAwareDrawable {
    fun setTint(tintColor: Int)

    fun setTintList(tint: ColorStateList?)

    fun setTintMode(tintMode: PorterDuff.Mode?)
}
